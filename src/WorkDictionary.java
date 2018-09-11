import enums.CharRange;
import enums.DictionaryFile;
import repositories.PropertiesDictionary;
import repositories.Repository;
import repositories.RepositoryFile;
import validators.ValidatorCharsAndLength;

public class WorkDictionary {

    public static void main(String[] args) {
        DictionaryFile file1 = DictionaryFile.FILE1;
        DictionaryFile file2 = DictionaryFile.FILE2;
        file1.setPath("\\dictionaries\\file1.txt");
        file2.setPath("\\dictionaries\\file2.txt");

        file1.setProperties(new PropertiesDictionary(4,
                new CharRange[]{CharRange.LATIN_CAPITAL, CharRange.LATIN_LOWERCASE}));
        file2.setProperties(new PropertiesDictionary(5,
                new CharRange[]{CharRange.NUMERALS}));

        Repository repository = new RepositoryFile(new DictionaryFile[]{file1, file2});

        DictionaryBehavior dictionary = new Dictionary(repository, new ValidatorCharsAndLength());

        ConsoleApplication application = new ConsoleApplication(dictionary);
        application.start();
    }

}
