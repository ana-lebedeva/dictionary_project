package fromBD.service;

import fromBD.DictionaryBD;
import fromBD.Record;

import java.util.HashMap;
import java.util.List;

public interface DictionaryService {
    List<DictionaryBD> getAll();
    void save(Record record);
    Record getRecord(int dictionaryID, String str);
    void deleteRecord(int dictionaryID, String word);
    HashMap<String, String> getRecords(int dictionaryID);
}
