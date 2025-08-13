package base;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {
    private static final Properties props = new Properties();

    static {
        try (InputStream input = PropertiesLoader.class.getClassLoader().getResourceAsStream("env.properties")) {
            if (input != null) {
                props.load(input);
            } else {
                System.err.println("[PropertiesLoader] env.properties not found, only system properties will be used.");
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load env.properties", e);
        }
    }

    public static String readEnvFile(String key) {
        // Priority: System property > env.properties file
        String systemValue = System.getProperty(key);
        if (systemValue != null) {
            return systemValue;
        }

        String fileValue = props.getProperty(key);
        if (fileValue != null) {
            return fileValue;
        }

        throw new RuntimeException("Property key not found: " + key);
    }
}
