package fromBD.service;

import fromBD.DictionaryBD;
import fromBD.Record;
import fromBD.dao.DictionaryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
@Service("storageService")
public class DictionaryServiceImpl implements DictionaryService {
    @Autowired
    private DictionaryDAO dao;

    @Override
    public List<DictionaryBD> getAll() {
        return dao.getAll();
    }

    @Transactional
    @Override
    public void save(Record record) {
        dao.save(record);
    }

    @Override
    public Record getRecord(int dictionaryID, String str) {
      return dao.getRecord(dictionaryID, str);
    }

    @Transactional
    @Override
    public void deleteRecord(int dictionaryID, String word) {
        dao.deleteRecord(dictionaryID, word);
    }

    @Override
    public HashMap<String, String> getRecords(int dictionaryID) {
        List<Record> records = dao.getRecords(dictionaryID);
        HashMap<String, String> result = new HashMap<>();
        for (Record record : records){
            result.put(record.getWord(), record.getTransletion());
        }
        return result;
    }

}
