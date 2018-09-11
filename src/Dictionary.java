import enums.DictionaryFile;
import enums.Status;
import repositories.Repository;
import validators.Validator;

import java.util.HashMap;

public class Dictionary implements DictionaryBehavior<DictionaryFile> {
    private Repository repository;
    private Validator validator;

    public Dictionary(Repository repository, Validator validator) {
        this.repository = repository;
        this.validator = validator;
    }


    @Override
    public String getValue(String key) {
        return repository.getValue(key);
    }

    @Override
    public Status deleteEntry(String key) {
        return repository.delete(key);
    }

    @Override
    public HashMap<String, String> getAllEntries() {
        return repository.getAll();
    }

    @Override
    public Status validateAndAddEntry(String key, String value) {
        Status checkKey = validator.checkKey(repository.getProperties(), key);
        Status checkValue = validator.checkValue(value);
        if (checkKey == Status.OK) {
            if (checkValue == Status.OK) {
                return repository.add(key, value);
            } else return checkValue;
        } else return checkKey;
    }

    @Override
    public DictionaryFile[] getDictionaries() {
        return (DictionaryFile[]) repository.getDictionaries();
    }

    @Override
    public void setActiveDictionary(DictionaryFile activeDictionary) {
        repository.setActiveDictionary(activeDictionary);
    }

}
