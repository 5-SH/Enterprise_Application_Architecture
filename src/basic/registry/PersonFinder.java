package basic.registry;

import java.util.HashMap;
import java.util.Map;

public class PersonFinder {
  private Map loadedMap = new HashMap();

  public Person find(Long id) {
    if (loadedMap.containsKey(id)) {
      return (Person) loadedMap.get(id);
    }
    return null;
  }

  public void load(Long id, Person person) {
    loadedMap.put(id, person);
  }
}
