package dictionaries;

import enums.DictionaryStructure;
import enums.Status;

import java.util.HashMap;
import java.util.List;

public interface DictionaryBehavior  <T extends DictionaryStructure>{
    String getValue(String key);
    Status deleteEntry(String key);
    HashMap<String, String> getAll();
    Status validateAndAddEntry(String key, String value);
    T[] getDictionaries();
    void setActiveDictionary(T activeDictionary);
    List<String> getAllEntries();
    Status deleteEntry(String key, String value);

}
