package fromBD;

import javax.persistence.*;

@Entity
@Table(name = "record")
public class Record {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String word;
    @Column(name = "translation")
    private String transletion;

    @ManyToOne
    @JoinColumn(name = "dictionary_id")
    private DictionaryBD dictionary;

    public Record(String word, String transletion, DictionaryBD dictionary) {
        this.word = word;
        this.transletion = transletion;
        this.dictionary = dictionary;
    }

    public Record() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setTransletion(String transletion) {
        this.transletion = transletion;
    }

    public DictionaryBD getDictionary() {
        return dictionary;
    }

    public void setDictionary(DictionaryBD dictionary) {
        this.dictionary = dictionary;
    }

    public String getWord() {
        return word;
    }

    public String getTransletion() {
        return transletion;
    }
}
