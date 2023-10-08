package org.example.utils;

import org.example.driver.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public final class ScreenshotUtils {

    private ScreenshotUtils(){}

    public static byte[] getScreenshot() {
        File screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);

        try {
            return  Files.readAllBytes(screenshot.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
