package org.example.reports;

import org.example.enums.ConfigProperties;
import org.example.enums.LogType;
import org.example.utils.PropertyUtils;
import org.example.utils.ScreenshotUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * FrameworkLogger is used to control loggin to console and extentReport file
 */
public final class FrameworkLogger {

    /**
     * Private constructor
     */
    private FrameworkLogger() {
    }

    private static final Logger logger = LoggerFactory.getLogger("");

    private static final Consumer<String> REPORT = message -> ExtentManager.getTest().log(message);
    private static final Consumer<String> CONSOLE = logger::info;
    private static final Consumer<String> LOG_REPORT_CONSOLE = REPORT.andThen(CONSOLE);

    private static final Map<LogType, Consumer<String>> MAP = new EnumMap<>(LogType.class);

    static {
        MAP.put(LogType.REPORT, REPORT);
        MAP.put(LogType.CONSOLE, CONSOLE);
        MAP.put(LogType.LOG_REPORT_CONSOLE, LOG_REPORT_CONSOLE);
    }

    /**
     * is used for logging with different combinations
     * @param status where to log a message
     * @param message message to log
     */
    public static void log(LogType status, String message) {
        MAP.get(status).accept(message);
    }

    /**
     * Make screenshot and log message
     * @param message message to log
     */
    public static void logWithScreenshot(String message){
        if (PropertyUtils.getProperty(ConfigProperties.PASSEDSTEPSCREENSHOTS).equalsIgnoreCase("yes")){
            ExtentManager.getTest()
                    .attach(ScreenshotUtils.getScreenshot(), "image/png","screenshot");
            log(LogType.LOG_REPORT_CONSOLE, message);
        }
        else
            log(LogType.LOG_REPORT_CONSOLE, message);
    }
}
