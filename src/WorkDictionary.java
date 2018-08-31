import enums.CharRange;
import java.util.ArrayList;

public class WorkDictionary {

    public static void main(String[] args) {
        ArrayList<DictionaryService> dictionaries = new ArrayList<>();

        dictionaries.add(new DictionaryService(
                "\\dictionaries\\file1.txt", "Dictionary1", 4,
                new CharRange[]{CharRange.LATIN_CAPITAL, CharRange.LATIN_LOWERCASE}));
        dictionaries.add(new DictionaryService(
                "\\dictionaries\\file2.txt", "Dictionary2", 5,
                new CharRange[]{CharRange.NUMERALS}));

        ConsoleApplication application = new ConsoleApplication(dictionaries);
        application.start();
    }

}
