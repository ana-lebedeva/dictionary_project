import enums.Message;
import enums.Status;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ConsoleApplication {
    private int menuItem;
    private DictionaryService dictionaryService;
    private ArrayList<DictionaryService> dictionaries;

    public ConsoleApplication(ArrayList<DictionaryService> dictionaries) {
        this.dictionaries = dictionaries;
    }

    public void start(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            if(!dictionaries.isEmpty())
                inputDictionary(br);
            else {
                showMessage(Status.NO_DICTIONARY);
                System.exit(0);
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
    private void addEntry(BufferedReader br) throws IOException {
        showMessage(Message.KEY_ENTRY);
        String key = br.readLine();
        showMessage(Message.VALUE_ENTRY);
        String value = br.readLine();
        showMessage(dictionaryService.addEntry(key, value));
    }

    private void searchEntry(BufferedReader br) throws IOException {
        showMessage(Message.KEY_ENTRY);
        String key = br.readLine();
        String value = dictionaryService.searchEntry(key);
        if (value == null)
            showMessage(Status.NOT_KEY);
        else{
            showMessage(Message.OUTPUT_VALUE);
            System.out.println(value);
        }
    }

    private void deleteEntry(BufferedReader br) throws IOException {
        showMessage(Message.KEY_ENTRY);
        String key = br.readLine();
        Status status = dictionaryService.deleteEntry(key);
        showMessage(status);
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
        for (DictionaryService service : dictionaries) {
            message += index + ". " + service.getDictionary().getName() + "\n";
            index++;
        }
        return message;
    }

    private void inputDictionary(BufferedReader br) throws IOException {
        menuItem = 0;
        int dictionaryItem = 0;
        boolean isCheck = false;
        showMessage(Message.DICTIONARY_SELECTION);
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

    private void viewDictionary(DictionaryService dictionary) {
        if (dictionary == null)
            showMessage(Status.NO_CONTENT);
        else {
            System.out.println(dictionary.toString());
        }
    }
}
