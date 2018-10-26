package fromBD.service;

import enums.Status;
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
    public Status save(Record record) {
        return dao.save(record);
    }

    @Override
    public List<Record> getRecord(int dictionaryID, String str) {
      return dao.getRecord(dictionaryID, str);
    }

    @Override
    public List<Record> getRecords(int dictionaryID) {
        return  dao.getRecords(dictionaryID);
    }
    @Transactional
    @Override
    public void deleteRecord(int dictionaryID, String word, String translation) {
        dao.deleteRecord(dictionaryID, word, translation);
    }

}
