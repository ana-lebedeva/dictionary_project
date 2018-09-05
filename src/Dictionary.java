import enums.Status;
import repositories.Repository;
import validators.Validator;

import java.util.HashMap;

public class Dictionary implements DictionaryBehavior {
    private Repository repository;
    private Validator validator;

    public Dictionary(Repository repository, Validator validator) {
        this.repository = repository;
        this.validator = validator;
    }


    @Override
    public String getValue(int id, String key) {
        return repository.getValue(id, key);
    }

    @Override
    public Status deleteEntry(int id, String key) {
        return repository.delete(id, key);
    }

    @Override
    public HashMap<String, String> getAllEntries(int id) {
        return repository.getAll(id);
    }

    @Override
    public Status validateAndAddEntry(int id, String key, String value) {
        if (validator.checkKey(repository.getProperties(id), key) == Status.OK) {
            if (validator.checkValue(value) == Status.OK) {
                return repository.add(id, key, value);
            } else return validator.checkValue(value);
        } else return validator.checkKey(repository.getProperties(id), key);
    }

    @Override
    public int[] getDictionaries() {
        return repository.getDictionaries();
    }

}
