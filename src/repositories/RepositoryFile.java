package repositories;

import enums.DictionaryFile;
import enums.Status;
import qualifiers.DictionaryFromFile;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

@DictionaryFromFile
public class RepositoryFile implements Repository<DictionaryFile> {
    private DictionaryFile activeDictionary;
    private boolean isNullActiveDictionary = true;
    DictionaryFile[] dictionaries;

    public RepositoryFile(DictionaryFile[] dictionaries) {
        this.dictionaries = dictionaries;
    }

    @Override
    public HashMap<String, String> getAll() {
        if (!isNullActiveDictionary) {
            String path = getPath();
            HashMap<String, String> dictionary = new HashMap<>();
            try (Scanner scanner = new Scanner(new InputStreamReader(new FileInputStream(path), "UTF-8"));) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] words = line.split("\\s");
                    dictionary.put(words[0], words[1]);
                }
            } catch (ArrayIndexOutOfBoundsException | FileNotFoundException | UnsupportedEncodingException e) {
                return null;
            }
            return dictionary;
        }
        return null;
    }

    private String getPath() {
        String tempPath = new File("").getAbsolutePath();
        String path = tempPath + activeDictionary.getPath();
        return path;
    }

    @Override
    public Status delete(String key) {
        if (!isNullActiveDictionary) {
            String path = getPath();
            File inputFile = new File(path);
            File tempFile = new File(new File("").getAbsolutePath() + "\\dictionaries\\TempFile.txt");
            try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                 BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            ) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] words = line.split("\\s");
                    if (words[0].equals(key)) continue;
                    writer.write(line + "\n");
                }

            } catch (IOException e) {
                return Status.NOT_FOUND;
            }
            inputFile.delete();
            tempFile.renameTo(new File(path));
            return Status.OK;
        }
        return Status.NO_ACTIVE_DICTIONARY;
    }

    @Override
    public Status add(String key, String value) {
        if (!isNullActiveDictionary) {
            String path = getPath();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true));) {
                writer.write(key + " " + value + "\n");
                return Status.OK;
            } catch (IOException e) {
                return Status.NOT_FOUND;
            }
        }
        return Status.NO_ACTIVE_DICTIONARY;
    }

    @Override
    public String getValue(String key) {
        if (!isNullActiveDictionary) {
            String path = getPath();
            try (Scanner scanner = new Scanner(new InputStreamReader(new FileInputStream(path), "UTF-8"));) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] words = line.split("\\s");
                    if (words[0].equals(key))
                        return words[1];
                }
            } catch (FileNotFoundException | UnsupportedEncodingException | ArrayIndexOutOfBoundsException e) {
                return null;
            }
        }
        return null;
    }

    @Override
    public DictionaryFile[] getDictionaries() {
        return dictionaries;
    }

    @Override
    public void setActiveDictionary(DictionaryFile activeDictionary) {
        this.activeDictionary = activeDictionary;
        isNullActiveDictionary = false;
    }

    @Override
    public PropertiesDictionary getProperties() {
        if (!isNullActiveDictionary) {
            return activeDictionary.getProperties();
        } else
            return null;
    }
}
