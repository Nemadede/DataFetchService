package org.datafetch.service.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertyFile {

    static Properties prop = new Properties();

    public static void setProp(){
        try {
            FileInputStream propFile = new FileInputStream("src/main/resources/config.properties");
            prop.load(propFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Properties getProp() {
        return prop;
    }
}
