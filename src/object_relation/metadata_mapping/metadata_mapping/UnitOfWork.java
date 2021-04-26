package object_relation.metadata_mapping.metadata_mapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UnitOfWork {
  private List<DomainObject> newObjects = new ArrayList<>();
  private List<DomainObject> dirtyObjects = new ArrayList<>();
  private List<DomainObject> removedObjects = new ArrayList<>();
  private Map<Long, DomainObject> loadedMap = new HashMap<>();

  private static ThreadLocal current = new ThreadLocal();

  public static void newCurrent() {
    setCurrent(new UnitOfWork());
  }

  public static void setCurrent(UnitOfWork uow) { current.set(uow); }

  public static UnitOfWork getCurrent() { return (UnitOfWork) current.get(); }

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

  public void registerClean(DomainObject obj) {
    assert obj.getId() == null : "id is null";
    loadedMap.put(obj.getId(), obj);
  }

  public void registerRemoved(DomainObject obj) {
    assert obj.getId() == null : "id is null";
    if (newObjects.remove(obj)) return;
    dirtyObjects.remove(obj);
    if (!removedObjects.contains(obj)) {
      removedObjects.add(obj);
    }
  }

  public boolean isLoaded(long key) {
    return loadedMap.containsKey(key);
  }

  public DomainObject getObject(long key) {
    if (loadedMap.containsKey(key)) return loadedMap.get(key);
    return null;
  }

  public void commit() {
    insertNew();
    updateDirty();
    deleteRemoved();
  }

  private void insertNew() {
    for (DomainObject obj : newObjects) {
      MapperRegistry.getMapper(obj.getClass().getName()).insert(obj);
    }
  }

  private void updateDirty() {
    for (DomainObject obj : dirtyObjects) {
      MapperRegistry.getMapper(obj.getClass().getName()).update(obj);
    }
  }

  private void deleteRemoved() {
    for (DomainObject obj : removedObjects) {
      MapperRegistry.getMapper(obj.getClass().getName()).delete(obj);
    }
  }
}
