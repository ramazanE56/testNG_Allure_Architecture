package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static final String CONFIG_FILE_PATH = "configuration.properties";
    private static final String CONFIG_FILE_PATH_2 = "configuration2.properties";
    private static Properties properties;
    private static Properties properties2;

    static {
        properties = loadProperties(CONFIG_FILE_PATH);
        properties2 = loadProperties(CONFIG_FILE_PATH_2);
    }

    private static Properties loadProperties(String filePath) {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(filePath)) {
            props.load(fis);
        } catch (IOException e) {
            System.out.println("Failed to load properties file: " + filePath);
        }
        return props;
    }

    public static String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            value = properties2.getProperty(key);
        }
        return value;
    }
}