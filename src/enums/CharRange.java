package enums;

public enum CharRange {
   NUMERALS(0x30, 0x39), LATIN_CAPITAL(0x41,0x5A),LATIN_LOWERCASE(0x61,0x7A),  CYRILLIC(0x410, 0x451);
    private int start;
    private int end;

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    CharRange(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
