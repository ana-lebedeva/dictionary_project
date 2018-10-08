package fromBD.dao;

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
    public void save(Record record) {
        em.persist(record);
    }

    @Override
    public Record getRecord(int dictionaryID, String str) {
        return em.createQuery("FROM Record r " +
                                 "WHERE r.dictionary.id = :id AND " +
                                 "(r.word = :str OR r.transletion = :str)",
                                 Record.class)
                .setParameter("id", dictionaryID)
                .setParameter("str", str)
                .getSingleResult();
    }

    @Override
    public void deleteRecord(int dictionaryID, String word) {
        Record record = getRecord(dictionaryID, word);
        em.remove(record);
    }

    @Override
    public List<Record> getRecords(int dictionaryID) {
        return em.createQuery("FROM Record r WHERE r.dictionary.id = :id", Record.class)
                .setParameter("id", dictionaryID)
                .getResultList();
    }

}
