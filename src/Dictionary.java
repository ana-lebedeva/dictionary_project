import enums.CharRange;
import enums.Status;

import java.util.HashMap;

public class Dictionary {
    private String name;
    private HashMap<String, String> entries = new HashMap<>();
    private int lengthKey;
    private CharRange[] charRanges;

    public Dictionary(String name, int lengthKey, CharRange[] charRanges) {
        this.name = name;
        this.lengthKey = lengthKey;
        this.charRanges = charRanges;
    }

    public String getName() {
        return name;
    }

    public void setEntry (String key, String value){
        entries.put(key, value);
    }

    public HashMap<String, String> getEntries() {
        return entries;
    }

    public int getLengthKey() {
        return lengthKey;
    }

    public CharRange[] getCharRanges() {
        return charRanges;
    }

}
