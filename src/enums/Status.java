package enums;

public enum Status {
    OK (0, "OK"),
    NOT_FOUND(1,"File not found."),
    NOT_KEY(2, "Key not found."),
    NO_CONTENT(3,"Dictionary is empty."),
    ERROR_INPUT(4, "Invalid input."),
    OUT_OF_BOUNDS (5, "Characters out of bounds."),
    INVALID_LENGTH(6, "The word is not a given length."),
    INVALID_DICTIONARY(7, "Invalid dictionary."),
    NO_DICTIONARY(8,"Dictionaries not found." );


    private final int code;
    private final String description;

    private Status(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code + ": " + description;
    }
}
