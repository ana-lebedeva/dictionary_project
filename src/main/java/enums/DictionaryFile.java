package enums;

import repositories.PropertiesDictionaryImpl;


public enum DictionaryFile implements DictionaryStructure {
    FILE1(
            "\\dictionaries\\file1.txt",
            new PropertiesDictionaryImpl(4,
                    new CharRangeImpl[]{CharRangeImpl.LATIN_CAPITAL, CharRangeImpl.LATIN_LOWERCASE}
                    )
    ), FILE2(
            "\\dictionaries\\file2.txt",
            new PropertiesDictionaryImpl(5,
                    new CharRangeImpl[]{CharRangeImpl.NUMERALS}
                    )
    );

    private String path;
    private PropertiesDictionaryImpl properties;

    DictionaryFile(String path, PropertiesDictionaryImpl properties) {
        this.path = path;
        this.properties = properties;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setProperties(PropertiesDictionaryImpl properties) {
        this.properties = properties;
    }

    @Override
    public PropertiesDictionaryImpl getProperties() {
        return properties;
    }

    @Override
    public String getName() {
        return toString();
    }

    public String getPath() {
        return path;
    }

}
