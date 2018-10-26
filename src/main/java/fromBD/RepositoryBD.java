package fromBD;

import enums.Status;
import fromBD.service.DictionaryService;
import repositories.PropertiesDictionary;
import repositories.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RepositoryBD implements Repository<DictionaryBD> {
    private DictionaryBD activeDictionary;
    private DictionaryService service;
    private boolean isNullActiveDictionary = true;

    public RepositoryBD(DictionaryService service) {
        this.service = service;
    }

    @Override
    public HashMap<String, String> getAll() {
        List<Record> records = service.getRecords(activeDictionary.getId());
        HashMap<String, String> result = new HashMap<>();
        for (Record record : records){
            result.put(record.getWord(), record.getTransletion());
        }
        return result;
    }

    @Override
    public Status delete(String key) {
        if (!isNullActiveDictionary){
            //TODO
        }
        return Status.NO_ACTIVE_DICTIONARY;
    }

    @Override
    public Status add(String key, String value) {
        if (!isNullActiveDictionary) {
            Record record = new Record(key, value, activeDictionary);
            return service.save(record);
        }
        return Status.NO_ACTIVE_DICTIONARY;
    }

    @Override
    public String getValue(String key) {
        try{
            String result = "";
            List<Record> records = service.getRecord(activeDictionary.getId(), key);
            for (Record record : records){
                result +=  record.getWord() + " - " + record.getTransletion() + "\r\n";

            }
            return result;
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

    @Override
    public List<String> getAllEntries() {
        List<Record> records = service.getRecords(activeDictionary.getId());
        List< String> result = new ArrayList<>();
        for (Record record : records){
            result.add(record.getWord()+ " - " + record.getTransletion());
        }
        return result;
    }

    @Override
    public Status delete(String key, String value) {
        if (!isNullActiveDictionary){
            try {
                service.deleteRecord(activeDictionary.getId(), key, value);
                return Status.OK;
            } catch (javax.persistence.NoResultException ex){
                return Status.NOT_KEY;
            }

        }
        return Status.NO_ACTIVE_DICTIONARY;
    }

}
