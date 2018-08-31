import enums.CharRange;
import enums.Status;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DictionaryService {
    final String PATH;
    Dictionary dictionary;

    public DictionaryService(String PATH, String name, int lengthKey, CharRange[] charRanges) {
        String tempPath = new File("").getAbsolutePath();
        this.PATH = tempPath + PATH;
        dictionary = new Dictionary(name, lengthKey, charRanges);
    }

    public String searchEntry(String key) {
        String value = dictionary.getEntries().get(key);
        return value;
    }

    public Status deleteEntry(String key) {
        String temp = dictionary.getEntries().remove(key);
        if (temp == null)
            return Status.NOT_KEY;
        overwriting();
        return Status.OK;
    }

    @Override
    public String toString() {
        String result = "";
        for (Map.Entry<String, String> entry : dictionary.getEntries().entrySet()) {
            result += entry.getKey() + " " + entry.getValue() + "\n";
        }
        return result;
    }

    private void overwriting() {
        File fold = new File(PATH);
        fold.delete();
        File fnew = new File(PATH);
        String source = toString();

        try {
            FileWriter f2 = new FileWriter(fnew, false);
            f2.write(source);
            f2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Status filling() {
        try (Scanner scanner = new Scanner(new InputStreamReader(new FileInputStream(PATH), "UTF-8"));) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] words = line.split("\\s");
                dictionary.setEntry(words[0], words[1]);
            }
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            return Status.NOT_FOUND;
        } catch (ArrayIndexOutOfBoundsException e) {
            return Status.INVALID_DICTIONARY;
        }
        return Status.OK;
    }


    public Status addEntry(String key, String value) {
        if (dictionary.getLengthKey() == 0 || dictionary.getLengthKey() == key.length()) {
                char[] charsKey = key.toCharArray();
                char[] charsValue = value.toCharArray();
                if (dictionary.getCharRanges() == null || checkCharsKey(charsKey)
                        && checkCharsValue(charsValue))
                    {
                    dictionary.setEntry(key, value);
                    overwriting();
                    return Status.OK;
                } else
                    return Status.OUT_OF_BOUNDS;
        } else
            return Status.INVALID_LENGTH;
    }

    private boolean checkCharsValue(char[] chars) {
        if (chars.length == 0)
            return false;
        for (char c : chars) {
            if (c < CharRange.CYRILLIC.getStart()
                    || c > CharRange.CYRILLIC.getEnd())
                return false;
        }
        return true;
    }

    private boolean checkCharsKey(char[] chars) {
        for (char c : chars) {
            boolean isHave = false;
            for (CharRange range : dictionary.getCharRanges()) {
                if (c >= range.getStart() && c <= range.getEnd())
                    isHave = true;
            }
            if (!isHave)
                return false;
        }
        return true;
    }

    public Dictionary getDictionary() {
        return dictionary;
    }
}
