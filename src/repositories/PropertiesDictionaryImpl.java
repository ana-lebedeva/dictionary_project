package repositories;

import enums.CharRange;
import enums.CharRangeImpl;

public class PropertiesDictionaryImpl implements PropertiesDictionary {
    private int lengthKey;
    private CharRangeImpl[] charRanges;

    public PropertiesDictionaryImpl(int lengthKey, CharRangeImpl[] charRanges) {
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

    @Override
    public void setCharRanges(CharRange[] charRanges) {
        this.charRanges = (CharRangeImpl[]) charRanges;
    }
}