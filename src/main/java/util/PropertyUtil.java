package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public class PropertyUtil {
    public static String getProperty(final String keyToFile) {
        Properties properties = new Properties();
        try (InputStream input = PropertyUtil.class.getClassLoader().getResourceAsStream("path.properties")) {
            properties.load(Objects.requireNonNull(input));
            return properties.getProperty(keyToFile);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
