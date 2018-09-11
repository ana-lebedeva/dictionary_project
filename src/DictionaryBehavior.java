import enums.DictionarySrtucture;
import enums.Status;
import repositories.PropertiesDictionary;

import java.util.HashMap;

public interface DictionaryBehavior  <T extends DictionarySrtucture>{
    String getValue(String key);
    Status deleteEntry(String key);
    HashMap<String, String> getAllEntries();
    Status validateAndAddEntry(String key, String value);
    T[] getDictionaries();
    void setActiveDictionary(T activeDictionary);

}
