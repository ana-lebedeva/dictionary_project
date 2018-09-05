package repositories;

import enums.Status;

import java.util.HashMap;

public interface Repository {

    HashMap<String, String> getAll(int id);
    Status delete (int id, String key);
    Status add (int id, String key, String value);
    String getValue (int id, String key);
    int[] getDictionaries();
    PropertiesDictionary getProperties (int id);

}
