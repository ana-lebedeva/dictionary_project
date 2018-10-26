package fromBD.dao;

import enums.Status;
import fromBD.DictionaryBD;
import fromBD.Record;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class DictionaryDAOImpl implements DictionaryDAO {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<DictionaryBD> getAll() {
        return em.createQuery("from DictionaryBD", DictionaryBD.class).getResultList();
    }

    @Override
    public Status save(Record record) {
        List<Record> temp = getRecords(record.getDictionary().getId(), record.getWord(), record.getTransletion());
        if(temp.size() != 0){
            return Status.ENTRY_EXISTS;
        } else {
            em.persist(record);
            return Status.OK;
        }

    }

    @Override
    public List<Record> getRecord(int dictionaryID, String str) {
        return em.createQuery("FROM Record r " +
                                 "WHERE r.dictionary.id = :id AND " +
                                 "(r.word = :str OR r.transletion = :str)",
                                 Record.class)
                .setParameter("id", dictionaryID)
                .setParameter("str", str)
                .getResultList();
    }

    private List<Record> getRecords(int dictionaryID, String key, String value) {
        return em.createQuery("FROM Record r " +
                        "WHERE r.dictionary.id = :id AND " +
                        "(r.word = :key AND r.transletion = :value)",
                Record.class)
                .setParameter("id", dictionaryID)
                .setParameter("key", key)
                .setParameter("value", value)
                .getResultList();
    }
    @Override
    public void deleteRecord(int dictionaryID, String word, String translation) {
        List<Record> records = getRecords(dictionaryID, word, translation);
        for (Record record : records){
            em.remove(record);
        }
    }

    @Override
    public List<Record> getRecords(int dictionaryID) {
        return em.createQuery("FROM Record r WHERE r.dictionary.id = :id", Record.class)
                .setParameter("id", dictionaryID)
                .getResultList();
    }

}
