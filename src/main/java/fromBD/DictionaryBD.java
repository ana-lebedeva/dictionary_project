package fromBD;

import enums.DictionaryStructure;
import repositories.PropertiesDictionary;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "dictionary")
public class DictionaryBD implements DictionaryStructure {

    private String name;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToMany(mappedBy = "dictionary")
    private List<Record> records;
    @OneToOne
    @JoinColumn(name = "rule_id")
    private Rule rule;
    //TODO
    @Override
    public PropertiesDictionary getProperties() {
        return rule;
    }

    public DictionaryBD() {
    }

    public DictionaryBD(String name, List<Record> records) {
        this.name = name;
        this.records = records;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }
}
