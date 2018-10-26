package dictionaries;

import enums.DictionaryStructure;
import enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import repositories.Repository;
import validators.Validator;

import java.util.HashMap;
import java.util.List;

public class Dictionary implements DictionaryBehavior{
    private Repository repository;
    private Validator validator;

    @Autowired
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
    public HashMap<String, String> getAll() {
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
    public DictionaryStructure[] getDictionaries() {
        return  repository.getDictionaries();
    }

    @Override
    public void setActiveDictionary(DictionaryStructure activeDictionary) {
        repository.setActiveDictionary(activeDictionary);
    }

    @Override
    public List<String> getAllEntries() {
        return repository.getAllEntries();
    }

    @Override
    public Status deleteEntry(String key, String value) {
       return repository.delete(key, value);
    }

}
