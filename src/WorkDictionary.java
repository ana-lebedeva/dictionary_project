import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class WorkDictionary {
    private static int menuItem;
    private static DictionaryService dictionaryService;
    private static ArrayList<DictionaryService> dictionaries = new ArrayList<>();

    public static void main(String[] args) {
        dictionaries.add(new DictionaryService(
                "\\dictionaries\\file1.txt", "Dictionary1", 4,
                        new CharRange[]{CharRange.LATIN_CAPITAL, CharRange.LATIN_LOWERCASE}));
        dictionaries.add(new DictionaryService(
                "\\dictionaries\\file2.txt", "Dictionary2", 5,
                        new CharRange[]{CharRange.NUMERALS}));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            inputDictionary(br);
        } catch (IOException e) {
            showMessage(Status.ERROR_INPUT);
        }
        while (menuItem != 6) {
            System.out.println("1. View dictionary;\n" +
                    "2. Remove entry;\n" +
                    "3. Add entry;\n" +
                    "4. Search entry;\n" +
                    "5. Back;\n" +
                    "6. End.");
            try {
                menuItem = Integer.parseInt(br.readLine());
                switch (menuItem) {
                    case 1:
                        viewDictionary(dictionaryService);
                        break;
                    case 2:
                        deleteEntry(br);
                        break;
                    case 3:
                        addEntry(br);
                        break;
                    case 4:
                        searchEntry(br);
                        break;
                    case 5:
                        inputDictionary(br);
                        break;
                    case 6:
                        br.close();
                        break;
                    default:
                        showMessage(Status.ERROR_INPUT);
                }
            } catch (NumberFormatException | IOException e) {
                showMessage(Status.ERROR_INPUT);
            }
        }
    }

    private static void addEntry(BufferedReader br) throws IOException {
        System.out.println("Enter key: ");
        String key = br.readLine();
        System.out.println("Enter value: ");
        String value = br.readLine();
        showMessage(dictionaryService.addEntry(key, value));
    }

    private static void searchEntry(BufferedReader br) throws IOException {
        System.out.print("Enter key: ");
        String key = br.readLine();
        String value = dictionaryService.searchEntry(key);
        if (value == null)
            showMessage(Status.NOT_KEY);
        else
            System.out.println("Value: " + value);
    }

    private static void deleteEntry(BufferedReader br) throws IOException {
        System.out.print("Enter key: ");
        String key = br.readLine();
        Status status = dictionaryService.deleteEntry(key);
        showMessage(status);
    }

    private static void showMessage(Status status) {
        System.out.println(status.getDescription());
    }

    private static String getMessage() {
        String message = "Select dictionary:\n";
        int index = 1;
        for (DictionaryService dictionary : dictionaries) {
            message += index + ". " + dictionary.getName() + "\n";
            index++;
        }
        return message;
    }

    private static void inputDictionary(BufferedReader br) throws IOException {
        menuItem = 0;
        int dictionaryItem = 0;
        boolean isCheck = false;
        String message = getMessage();
        while (!isCheck) {
            System.out.print(message);
            try {
                dictionaryItem = Integer.parseInt(br.readLine()) - 1;
                if (dictionaries.size() <= dictionaryItem) {
                    showMessage(Status.ERROR_INPUT);
                    isCheck = false;
                } else {
                    isCheck = true;
                }
            } catch (NumberFormatException e) {
                showMessage(Status.ERROR_INPUT);
            }
        }

        dictionaryService = dictionaries.get(dictionaryItem);
        Status status = dictionaryService.filling();
        showMessage(status);
        if (status != Status.OK)
            inputDictionary(br);
    }

    private static void viewDictionary(DictionaryService dictionary) {
        if (dictionary == null)
            showMessage(Status.NO_CONTENT);
        else {
            System.out.println(dictionary.toString());
        }
    }
}
