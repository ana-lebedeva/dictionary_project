package repositories;

import enums.Status;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class RepositoryFile implements Repository {
    List<String> paths;
    List<PropertiesDictionary> propertiesDictionaries;

    public RepositoryFile(List<String> paths, List<PropertiesDictionary> propertiesDictionaries) {
        this.paths = paths;
        this.propertiesDictionaries = propertiesDictionaries;
    }

    @Override
    public HashMap<String, String> getAll(int id)  {
        String tempPath = new File("").getAbsolutePath();
        String path = tempPath + paths.get(id);
        HashMap<String, String> dictionary = new HashMap<>();
        try (Scanner scanner = new Scanner(new InputStreamReader(new FileInputStream(path), "UTF-8"));) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] words = line.split("\\s");
                dictionary.put(words[0], words[1]);
            }
        } catch (ArrayIndexOutOfBoundsException | FileNotFoundException | UnsupportedEncodingException   e) {
            return null;
        }
        return dictionary;
    }

    @Override
    public Status delete(int id, String key) {
        String tempPath = new File("").getAbsolutePath();
        String path = tempPath + paths.get(id);
        File inputFile = new File(path);
        File tempFile = new File(tempPath + "\\dictionaries\\TempFile.txt");
        try(BufferedReader reader = new BufferedReader(new FileReader(inputFile));
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

    @Override
    public Status add(int id, String key, String value) {
        String tempPath = new File("").getAbsolutePath();
        String path = tempPath + paths.get(id);
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(path, true));){
            writer.write("\n" + key + " " + value);
            return Status.OK;
        } catch (IOException e) {
            return Status.NOT_FOUND;
        }
    }

    @Override
    public String getValue(int id, String key) {
        String tempPath = new File("").getAbsolutePath();
        String path = tempPath + paths.get(id);
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
        return null;
    }

    @Override
    public int[] getDictionaries() {
        int[] result = new int[paths.size()];
        for (int i = 0; i < result.length; result[i] = i, i++);
        return result;
    }

    @Override
    public PropertiesDictionary getProperties(int id) {
        return propertiesDictionaries.get(id);
    }
}
