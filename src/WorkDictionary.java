import enums.CharRange;
import repositories.PropertiesDictionary;
import repositories.Repository;
import repositories.RepositoryFile;
import validators.ValidatorCharsAndLength;

import java.util.ArrayList;
import java.util.List;

public class WorkDictionary {

    public static void main(String[] args) {
        List<String> paths = new ArrayList<>();
        paths.add("\\dictionaries\\file1.txt");
        paths.add("\\dictionaries\\file2.txt");

        List<PropertiesDictionary> propertiesDictionaries = new ArrayList<>();
        propertiesDictionaries.add(new PropertiesDictionary(4,
                new CharRange[]{CharRange.LATIN_CAPITAL, CharRange.LATIN_LOWERCASE}));
        propertiesDictionaries.add(new PropertiesDictionary(5,
                new CharRange[]{CharRange.NUMERALS}));

        Repository repository = new RepositoryFile(paths, propertiesDictionaries);

        DictionaryBehavior dictionary = new Dictionary(repository, new ValidatorCharsAndLength());

        ConsoleApplication application = new ConsoleApplication(dictionary);
        application.start();
    }

}
