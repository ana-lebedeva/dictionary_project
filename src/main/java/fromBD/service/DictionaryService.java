package fromBD.service;

import enums.Status;
import fromBD.DictionaryBD;
import fromBD.Record;

import java.util.HashMap;
import java.util.List;

public interface DictionaryService {
    List<DictionaryBD> getAll();
    Status save(Record record);
    List<Record> getRecord(int dictionaryID, String str);
    List<Record> getRecords(int dictionaryID);
    void deleteRecord(int dictionaryID, String word, String translation);
}
