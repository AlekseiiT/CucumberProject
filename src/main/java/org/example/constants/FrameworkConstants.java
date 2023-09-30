package org.example.constants;

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
    private static final int EXPLICIT_WAIT = 10;

    /**
     * Getter for property config path
     *
     * @return property config path
     */
    public static String getConfigfilepath() {
        return CONFIG_FILE_PATH;
    }

    /**
     * Getter for explicit wait time. Can be changed depending on the needs
     *
     * @return explicit wait amount
     */
    public static int getExplicitWait() {
        return EXPLICIT_WAIT;
    }

}
