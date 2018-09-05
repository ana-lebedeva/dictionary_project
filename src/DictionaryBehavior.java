import enums.Status;

import java.util.HashMap;

public interface DictionaryBehavior {
    String getValue(int id, String key);
    Status deleteEntry(int id, String key);
    HashMap<String, String> getAllEntries(int id);
    Status validateAndAddEntry(int id, String key, String value);
    int[] getDictionaries();
}
