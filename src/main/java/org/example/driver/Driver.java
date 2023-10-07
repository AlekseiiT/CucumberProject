package org.example.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.client.ClientUtil;
import org.example.enums.ConfigProperties;
import org.example.utils.PropertyUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Objects;

/**
 * Driver class is responsible for invoking and closing browsers.<br>
 * Driver class is also responsible for setting the driver variable to DriverManager which handles the thread
 * safety for the webDriver instance.<br>
 */

public final class Driver {

    private Driver() {
    }

    public static void initDriver() {
        boolean isHeadlessMode = PropertyUtils.getProperty(ConfigProperties.HEADLESSMODE).equalsIgnoreCase("true");

        if (Objects.isNull(DriverManager.getDriver())) {
            ProxyManager.createProxy();
            BrowserMobProxy browserMobProxy = ProxyManager.getProxy();
            Proxy seleniumProxy = ClientUtil.createSeleniumProxy(browserMobProxy);

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--disable-web-security");
            options.setProxy(seleniumProxy);

            if (isHeadlessMode)
                options.addArguments("--headless");

            WebDriverManager.chromedriver().setup();
            DriverManager.setDriver(new ChromeDriver(options));

            DriverManager.getDriver().manage().window().maximize();

            if (isHeadlessMode)
                DriverManager.getDriver().manage().window().setSize(new Dimension(1280, 720));

            DriverManager.getDriver().get(PropertyUtils.getProperty(ConfigProperties.URL));
            browserMobProxy.newHar("requestHistory");
        }
    }

    /**
     * This method will be called to close the driver in BaseTest in @AfterMethod method
     */
    public static void quitDriver() {
        if (Objects.nonNull(DriverManager.getDriver())) {
            DriverManager.getDriver().quit();
            DriverManager.unload();
            ProxyManager.unload();
        }
    }
}
