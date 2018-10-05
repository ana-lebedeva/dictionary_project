package fromBD;

import enums.CharRange;

import javax.persistence.*;

@Entity
@Table(name = "characters")
public class Characters implements CharRange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(name = "range_start")
    private int start;
    @Column(name = "range_end")
    private int end;

    @ManyToOne
    @JoinColumn(name = "rule_id")
    private Rule rule;

    @Override
    public int getStart() {
        return start;
    }

    @Override
    public int getEnd() {
        return end;
    }

    @Override
    public String getName() {
        return name;
    }
}
