package fromBD.dao;

import enums.Status;
import fromBD.DictionaryBD;
import fromBD.Record;

import java.util.List;

public interface DictionaryDAO {
    List<DictionaryBD> getAll();
    Status save(Record record);
    List<Record> getRecord(int dictionaryID, String str);
    void deleteRecord(int dictionaryID, String word, String translation);
    List<Record> getRecords(int dictionaryID);
}
