package ru.avalon.javapp.devj140.writerjavafx.propPacket;

import java.io.*;
import java.util.Properties;

public class PropApp {
    static File file;
    static Properties properties;
    static {
        properties = new Properties();
        file = new File("propApp.prop");
        if (!file.exists()) try {
            file.createNewFile();
        }  catch (IOException e) {

        }
    }

    public static String getValue(String key) {
        String value = "";
        try (FileReader fileReader = new FileReader(file)) {
            properties.load(fileReader);
            value = properties.getProperty(key);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }

    public static void setValue(String key, String value) {
        properties.setProperty(key, value);
        try(FileWriter fileWriter = new FileWriter(file)) {
            properties.store(fileWriter, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}