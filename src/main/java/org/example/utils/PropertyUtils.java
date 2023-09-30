package org.example.utils;

import org.example.constants.FrameworkConstants;
import org.example.PropertyFileUsageException;
import org.example.enums.ConfigProperties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

/**
 * PropertyUtils is used to get config values from config file in form of map
 */
public class PropertyUtils {

    private static final Properties property = new Properties();
    private static final Map<String, String> CONFIG_MAP = new HashMap<>();

    private PropertyUtils() {
    }

    static {
        try (FileInputStream file = new FileInputStream(FrameworkConstants.getConfigfilepath())) {

            property.load(file);
            //converting to HashMap
            property.forEach((key, value) -> CONFIG_MAP.put(String.valueOf(key), String.valueOf(value)));

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public static String get(ConfigProperties key){
        if (Objects.isNull(CONFIG_MAP.get(key.name().toLowerCase()))){
            throw new PropertyFileUsageException("Property name " + key + " is not found. Please check config.properties");
        }
        return CONFIG_MAP.get(key.name().toLowerCase());
    }
}
