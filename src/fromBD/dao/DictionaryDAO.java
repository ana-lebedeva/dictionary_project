package fromBD.dao;

import fromBD.DictionaryBD;
import fromBD.Record;

import java.util.List;

public interface DictionaryDAO {
    List<DictionaryBD> getAll();
    void save(Record record);
    Record getRecord(int dictionaryID, String str);
    void deleteRecord(int dictionaryID, String word);
    List<Record> getRecords(int dictionaryID);
}
