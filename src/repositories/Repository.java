package repositories;

import enums.DictionarySrtucture;
import enums.Status;

import java.util.HashMap;

public interface Repository <T extends DictionarySrtucture>{

    HashMap<String, String> getAll();
    Status delete (String key);
    Status add (String key, String value);
    String getValue (String key);
    T[] getDictionaries();
    PropertiesDictionary getProperties();
    void setActiveDictionary(T activeDictionary);
}
