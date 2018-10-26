package enums;

import repositories.PropertiesDictionary;

import java.io.Serializable;

public interface DictionaryStructure extends Serializable {
    PropertiesDictionary getProperties();
    String getName();
}
