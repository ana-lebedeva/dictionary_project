import enums.Message;
import enums.Status;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class ConsoleApplication {
    private int menuItem;
    private DictionaryBehavior dictionary;
    private int id;

    public ConsoleApplication(DictionaryBehavior dictionary) {
        this.dictionary = dictionary;
    }

    public void start() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            if (dictionary.getDictionaries() == null || dictionary.getDictionaries().length == 0) {
                showMessage(Status.NO_DICTIONARY);
                System.exit(0);
            } else {
                inputDictionary(br);
            }
        } catch (IOException e) {
            showMessage(Status.ERROR_INPUT);
        }
        while (menuItem != 6) {
            showMessage(Message.MENU);
            try {
                menuItem = Integer.parseInt(br.readLine());
                switch (menuItem) {
                    case 1:
                        viewDictionary(dictionary);
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

    private void addEntry(BufferedReader br) throws IOException {
        showMessage(Message.KEY_ENTRY);
        String key = br.readLine();
        showMessage(Message.VALUE_ENTRY);
        String value = br.readLine();
        showMessage(dictionary.validateAndAddEntry(id, key, value));
    }

    private void searchEntry(BufferedReader br) throws IOException {
        showMessage(Message.KEY_ENTRY);
        String key = br.readLine();
        String value = dictionary.getValue(id, key);
        if (value == null)
            showMessage(Status.NOT_KEY);
        else {
            showMessage(Message.OUTPUT_VALUE);
            System.out.println(value);
        }
    }

    private void deleteEntry(BufferedReader br) throws IOException {
        showMessage(Message.KEY_ENTRY);
        String key = br.readLine();
        showMessage(dictionary.deleteEntry(id, key));
    }

    private void showMessage(Status status) {
        System.out.println(status.getDescription());
    }

    private void showMessage(Message message) {
        System.out.println(message.getDescription());
    }

    private String getMessage() {
        String message = "";
        int index = 1;
        for (int i : dictionary.getDictionaries()) {
            message += index + ". Dictionary with index " + i + "\n";
            index++;
        }
        return message;
    }

    private boolean setID(int id) {
        boolean isRight = false;
        for (int i : dictionary.getDictionaries())
            if (i == id) {
                isRight = true;
                this.id = id;
                break;
            }
        return isRight;
    }

    private void inputDictionary(BufferedReader br) throws IOException {
        menuItem = 0;
        int id = 0;
        boolean isCheck = false;
        showMessage(Message.DICTIONARY_SELECTION);
        String message = getMessage();
        while (!isCheck) {
            System.out.print(message);
            try {
                id = Integer.parseInt(br.readLine()) - 1;
                if (!setID(id)) {
                    showMessage(Status.ERROR_INPUT);
                    isCheck = false;
                } else {
                    isCheck = true;
                }
            } catch (NumberFormatException e) {
                showMessage(Status.ERROR_INPUT);
            }
        }
    }

    private void viewDictionary(DictionaryBehavior dictionary) {
        if (dictionary == null)
            showMessage(Status.NO_CONTENT);
        else {
            HashMap<String, String> entries = dictionary.getAllEntries(id);
            if (entries != null) {
                for (Map.Entry<String, String> entry : entries.entrySet()) {
                    System.out.println(entry.getKey() + " " + entry.getValue());
                }
            }
            else
                showMessage(Status.INVALID_DICTIONARY);
        }
    }
}
