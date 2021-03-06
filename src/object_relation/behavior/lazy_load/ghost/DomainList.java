package object_relation.behavior.lazy_load.ghost;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class DomainList extends DomainObject implements List {
  private List source;
  private ListLoader loader;

  public DomainList(Long key) {
    super(key);
  }


  protected void load() {
    if (isGhost()) {
      markLoading();
      source = loader.load();
      markLoaded();
    }
  }

  public void setLoader(ListLoader loader) {
    this.loader = loader;
  }

  @Override
  public int size() {
    load();
    return source.size();
  }

  @Override
  public boolean isEmpty() {
    load();
    return source.isEmpty();
  }

  @Override
  public boolean contains(Object o) {
    return false;
  }

  @Override
  public Iterator iterator() {
    load();
    return source.iterator();
  }

  @Override
  public Object[] toArray() {
    return new Object[0];
  }

  @Override
  public Object[] toArray(Object[] a) {
    return new Object[0];
  }

  @Override
  public boolean add(Object o) {
    return false;
  }

  @Override
  public boolean remove(Object o) {
    return false;
  }

  @Override
  public boolean containsAll(Collection c) {
    return false;
  }

  @Override
  public boolean addAll(Collection c) {
    return false;
  }

  @Override
  public boolean addAll(int index, Collection c) {
    return false;
  }

  @Override
  public boolean removeAll(Collection c) {
    return false;
  }

  @Override
  public boolean retainAll(Collection c) {
    return false;
  }

  @Override
  public void clear() {

  }

  @Override
  public Object get(int index) {
    load();
    return source.get(index);
  }

  @Override
  public Object set(int index, Object element) {
    load();
    return source.set(index, element);
  }

  @Override
  public void add(int index, Object element) {
    load();
    source.add(index, element);
  }

  @Override
  public Object remove(int index) {
    load();
    return source.remove(index);
  }

  @Override
  public int indexOf(Object o) {
    return 0;
  }

  @Override
  public int lastIndexOf(Object o) {
    return 0;
  }

  @Override
  public ListIterator listIterator() {
    return null;
  }

  @Override
  public ListIterator listIterator(int index) {
    return null;
  }

  @Override
  public List subList(int fromIndex, int toIndex) {
    return null;
  }
}
