package pokemonbattle.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MyFile {

    public static String ReadFileXML(String Percorso) throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader(Percorso));
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        String ls = System.getProperty("line.separator");

        try {
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }
            return stringBuilder.toString();
        } finally {
            reader.close();
        }

    }

    public static int CountElement(String Directory) {
        int Count = new File(Directory).list().length;
        return Count;
    }

    public static boolean FileExist(String Directory) {
        int Count = new File(Directory).list().length;
        if (Count != 0) {
            return false;
        }
        return true;
    }

    public static String[] getListOfFiles(String path) {
        File directory = new File(path);
        File files[] = directory.listFiles();
        String filesName[] = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                filesName[i] = files[i].getName();
            }
        }
        return filesName;
    }
}
