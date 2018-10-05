package repositories;

import enums.CharRange;

public interface PropertiesDictionary {
    int getLengthKey();
    void setLengthKey(int lengthKey);
    CharRange[] getCharRanges();
    void setCharRanges(CharRange[] charRanges);
}
