package enums;

import repositories.PropertiesDictionary;


public enum DictionaryFile implements DictionarySrtucture{
    FILE1(
            "\\dictionaries\\file1.txt",
            new PropertiesDictionary(4,
                    new CharRange[]{CharRange.LATIN_CAPITAL, CharRange.LATIN_LOWERCASE}
                    )
    ), FILE2(
            "\\dictionaries\\file2.txt",
            new PropertiesDictionary(5,
                    new CharRange[]{CharRange.NUMERALS}
                    )
    );

    private String path;
    private PropertiesDictionary properties;

    DictionaryFile(String path, PropertiesDictionary properties) {
        this.path = path;
        this.properties = properties;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setProperties(PropertiesDictionary properties) {
        this.properties = properties;
    }

    @Override
    public PropertiesDictionary getProperties() {
        return properties;
    }

    public String getPath() {
        return path;
    }

}
