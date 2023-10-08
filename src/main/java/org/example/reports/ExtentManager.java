package org.example.reports;

import io.cucumber.java.Scenario;

/**
 * ExtentManager class is used for storing Scenario in ThreadLocal
 */
public class ExtentManager {

    /**
     * Private constructor
     */
    private ExtentManager() {}

    /**
     * ThreadLocal deals with thread issues
     */
    private static ThreadLocal<Scenario> extTest = new ThreadLocal<>();

    /**
     * Getter for test scenario
     * @return Scenario instance
     */
    public static Scenario getTest() {
        return extTest.get();
    }

    /**
     * Setter for test scenario
     * @param scenario Scenario instance
     */
    public static void setTest(Scenario scenario){
        extTest.set(scenario);
    }

    static void unload(){
        extTest.remove();
    }
}
