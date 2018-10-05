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

    public String getWord() {
        return word;
    }

    public String getTransletion() {
        return transletion;
    }
}
