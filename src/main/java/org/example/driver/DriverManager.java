package org.example.driver;

import org.openqa.selenium.WebDriver;

/**
 * DriverManager is used to set and get webDriver from ThreadLocals variable. Instance of
 * webDriver comes from {@link Driver} class
 */
public class DriverManager {
    /**
     * Private constructor
     */
    private DriverManager(){}
    /**
     * ThreadLocal variable to deal with thread safety issues
     */
    private static ThreadLocal<WebDriver> dr = new ThreadLocal<>();

    /**
     * Getter for the WebDriver
     * @return WebDriver instance
     */
    public static WebDriver getDriver() {
        return dr.get();
    }

    /**
     * Setter for WebDriver
     * @param driverref webDriver referense from {@link Driver} class
     */
    public static void setDriver(WebDriver driverref){
        dr.set(driverref);
    }

    /**
     * Unloads threadLocal variable
     */
    public static void unload(){
        dr.remove();
    }
}
