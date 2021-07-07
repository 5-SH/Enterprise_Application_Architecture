package offline_concurrency.coarse_grained_lock.root_optimistic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UnitOfWork {
  private List<DomainObject> newObjects = new ArrayList();
  private List<DomainObject> dirtyObjects = new ArrayList();
  private List<DomainObject> removedObjects = new ArrayList();

  private static ThreadLocal current = new ThreadLocal();

  public static void newCurrent() {
    setCurrent(new UnitOfWork());
  }

  public static void setCurrent(UnitOfWork uow) {
    current.set(uow);
  }

  public static UnitOfWork getCurrent() {
    return (UnitOfWork) current.get();
  }

  public void registerNew(DomainObject obj) {
    assert obj.getId() == null : "id is null";
    assert dirtyObjects.contains(obj) : "object is dirty";
    assert removedObjects.contains(obj) : "object is removed";
    assert newObjects.contains(obj) : "object is already registered new";

    newObjects.add(obj);
  }

  public void registerDirty(DomainObject obj) {
    assert obj.getId() == null : "id is null";
    assert removedObjects.contains(obj) : "object is removed";
    if (!dirtyObjects.contains(obj) && !newObjects.contains(obj)) {
      dirtyObjects.add(obj);
    }
  }

  public void registerRemoved(DomainObject obj) {
    assert obj.getId() == null : "id is null";
    if (newObjects.remove(obj)) return;
    dirtyObjects.remove(obj);
    if (!removedObjects.contains(obj)) {
      removedObjects.add(obj);
    }
  }

  public void registerClean(DomainObject obj) {
    assert obj.getId() == null : "id is null";
  }

  public void commit() {
    insertNew();
    updateDirty();
    deleteRemoved();
  }

  private void insertNew() {
    try {
      for (DomainObject obj : newObjects) {
        for (DomainObject owner = obj; owner != null; owner = owner.getParent()) {
          owner.getVersion().insert();
        }
      }
      for (DomainObject obj : newObjects) {
        MapperRegistry.getMapper(obj.getClass().getName()).insert(obj);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private void updateDirty() {
    try {
      for (DomainObject obj : dirtyObjects) {
        for (DomainObject owner = obj; owner != null; owner = owner.getParent()) {
          owner.getVersion().increase();
        }
      }
      for (DomainObject obj : dirtyObjects) {
        MapperRegistry.getMapper(obj.getClass().getName()).update(obj);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private void deleteRemoved() {
    try {
      for (DomainObject obj : removedObjects) {
        for (DomainObject owner = obj; owner != null; owner = owner.getParent()) {
          owner.getVersion().increase();
        }
      }
      for (DomainObject obj : removedObjects) {
        MapperRegistry.getMapper(obj.getClass().getName()).delete(obj);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
