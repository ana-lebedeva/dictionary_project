import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DictionaryService {
    private String name;
    private HashMap<String, String> dictionary = new HashMap<>();
    private int lengthKey;
    private CharRange[] charRanges;
    final String PATH;

    public DictionaryService(String PATH, String name, int lengthKey, CharRange[] charRanges) {
        String tempPath = new File("").getAbsolutePath();
        this.PATH = tempPath + PATH;
        this.name = name;
        this.lengthKey = lengthKey;
        this.charRanges = charRanges;
    }

    public String searchEntry(String key) {
        String value = dictionary.get(key);
        return value;
    }

    public Status deleteEntry(String key) {
        String temp = dictionary.remove(key);
        if (temp == null)
            return Status.NOT_KEY;
        overwriting();
        return Status.OK;
    }

    @Override
    public String toString() {
        String result = "";
        for (Map.Entry<String, String> entry : dictionary.entrySet()) {
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
                dictionary.put(words[0], words[1]);
            }
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            return Status.NOT_FOUND;
        } catch (ArrayIndexOutOfBoundsException e) {
            return Status.INVALID_DICTIONARY;
        }
        return Status.OK;
    }

    public HashMap<String, String> getDictionary() {
        return dictionary;
    }


    public Status addEntry(String key, String value) {
        if (lengthKey == 0 || lengthKey == key.length()) {
                char[] charsKey = key.toCharArray();
                char[] charsValue = value.toCharArray();
                if (charRanges == null || checkCharsKey(charsKey)
                        && checkCharsValue(charsValue))
                    {
                    dictionary.put(key, value);
                    overwriting();
                    return Status.OK;
                } else
                    return Status.OUT_OF_BOUNDS;
        } else
            return Status.INVALID_LENGTH;
    }

    private boolean checkCharsValue(char[] chars) {
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
            for (CharRange range : charRanges) {
                if (c >= range.getStart() && c <= range.getEnd())
                    isHave = true;
            }
            if (!isHave)
                return false;
        }
        return true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLengthKey() {
        return lengthKey;
    }

    public void setLengthKey(int lengthKey) {
        this.lengthKey = lengthKey;
    }

    public CharRange[] getCharRanges() {
        return charRanges;
    }

    public void setCharRanges(CharRange[] charRanges) {
        this.charRanges = charRanges;
    }
}
