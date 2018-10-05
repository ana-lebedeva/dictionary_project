import enums.DictionaryStructure;
import enums.Status;

import java.util.HashMap;

public interface DictionaryBehavior  <T extends DictionaryStructure>{
    String getValue(String key);
    Status deleteEntry(String key);
    HashMap<String, String> getAllEntries();
    Status validateAndAddEntry(String key, String value);
    T[] getDictionaries();
    void setActiveDictionary(T activeDictionary);

}
