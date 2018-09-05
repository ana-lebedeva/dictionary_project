package repositories;

import enums.CharRange;

public class PropertiesDictionary {
    private int lengthKey;
    private CharRange[] charRanges;

    public PropertiesDictionary(int lengthKey, CharRange[] charRanges) {
        this.lengthKey = lengthKey;
        this.charRanges = charRanges;
    }

    public int getLengthKey() {
        return lengthKey;
    }

    public void setLengthKey(int lengthKey) {
        this.lengthKey = lengthKey;
    }

    public CharRange[] getCharRanges() {
        return charRanges;
    }

    public void setCharRanges(CharRange[] charRanges) {
        this.charRanges = charRanges;
    }
}
