package fromBD;

import enums.Status;
import repositories.PropertiesDictionary;
import repositories.Repository;

import java.util.HashMap;
import java.util.List;

public class RepositoryBD implements Repository<DictionaryBD> {
    private DictionaryBD activeDictionary;
    private  DictionaryService service;
    private boolean isNullActiveDictionary = true;

    public RepositoryBD(DictionaryService service) {
        this.service = service;
    }

    @Override
    public HashMap<String, String> getAll() {
        return service.getRecords(activeDictionary.getId());
    }

    @Override
    public Status delete(String key) {
        if (!isNullActiveDictionary){
            try {
                service.deleteRecord(activeDictionary.getId(), key);
                return Status.OK;
            } catch (javax.persistence.NoResultException ex){
                return Status.NOT_KEY;
            }

        }
        return Status.NO_ACTIVE_DICTIONARY;
    }

    @Override
    public Status add(String key, String value) {
        if (!isNullActiveDictionary) {
            Record record = new Record(key, value, activeDictionary);
            service.save(record);
            return Status.OK;
        }
        return Status.NO_ACTIVE_DICTIONARY;
    }

    @Override
    public String getValue(String key) {
        try{
            Record record =service.getRecord(activeDictionary.getId(), key);
            return  record.getWord() + " " + record.getTransletion();
        } catch (javax.persistence.NoResultException ex){
            return null;
        }

    }

    @Override
    public DictionaryBD[] getDictionaries() {
        List<DictionaryBD> list = service.getAll();
        return list.toArray(new DictionaryBD[list.size()]);
    }

    @Override
    public PropertiesDictionary getProperties() {
       return activeDictionary.getProperties();
    }

    @Override
    public void setActiveDictionary(DictionaryBD activeDictionary) {
        this.activeDictionary = activeDictionary;
        isNullActiveDictionary = false;
    }

}
