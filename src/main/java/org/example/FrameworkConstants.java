package org.example;

/**
 * FrameworkConstants has all constants that are used in the TestFramework.<br>
 * To use constants use available getter methods.<br>
 */
public class FrameworkConstants {
    /**
     * private constructor
     */
    private FrameworkConstants() {
    }

    private static final String RESOURCES_PATH = System.getProperty("user.dir");
    private static final String CONFIG_FILE_PATH = RESOURCES_PATH + "/src/test/resources/config/config.properties";

    /**
     * Getter for property config path
     *
     * @return property config path
     */
    public static String getConfigfilepath() {
        return CONFIG_FILE_PATH;
    }
}
