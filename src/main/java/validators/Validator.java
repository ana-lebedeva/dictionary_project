package validators;

import enums.Status;
import repositories.PropertiesDictionary;

public interface Validator {
    Status checkKey(PropertiesDictionary properties, String key);
    Status checkValue (String value);
}
