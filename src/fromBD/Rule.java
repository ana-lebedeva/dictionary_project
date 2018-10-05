package fromBD;

import enums.CharRange;
import repositories.PropertiesDictionary;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "rule")
public class Rule implements PropertiesDictionary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "length")
    private int lengthKey;
    @OneToMany (mappedBy = "rule", fetch = FetchType.EAGER)
    private List<Characters> characters;
    @OneToOne(mappedBy = "rule")
    private DictionaryBD dictionary;


    @Override
    public int getLengthKey() {
        return lengthKey;
    }

    @Override
    public void setLengthKey(int lengthKey) {
        this.lengthKey = lengthKey;
    }

    @Override
    public CharRange[] getCharRanges() {
        return characters.toArray(new CharRange[characters.size()]);
    }

    @Override
    public void setCharRanges(CharRange[] charRanges) {
        Arrays.asList(charRanges);
    }

}
