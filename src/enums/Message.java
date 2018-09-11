package enums;

public enum Message {
    KEY_ENTRY("Enter key: "),
    VALUE_ENTRY("Enter value: "),
    OUTPUT_VALUE("Value: "),
    DICTIONARY_SELECTION("Select dictionary:"),
    MENU("1. View dictionary;\n" +
            "2. Remove entry;\n" +
            "3. Add entry;\n" +
            "4. Search entry;\n" +
            "5. Back;\n" +
            "6. End."),
    LENGTH("The length should be : "),
    ALPHABETS("Available alphabets for "),
    KEY ("KEY"),
    VALUE("VALUE");
    private final String description;

    Message(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }
}
