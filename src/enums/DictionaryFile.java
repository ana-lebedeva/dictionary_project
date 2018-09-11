package enums;

import repositories.PropertiesDictionary;


public enum DictionaryFile implements DictionarySrtucture{
    FILE1, FILE2;

    private String path;
    private PropertiesDictionary properties;

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
