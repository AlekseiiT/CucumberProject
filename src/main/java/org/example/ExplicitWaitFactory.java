package org.example;

import org.example.constants.FrameworkConstants;
import org.example.driver.DriverManager;
import org.example.enums.WaitStrategy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * Factory to find an element with different expected conditions.<br>
 * Can be used for WebElement and WebElements.<br>
 */
public class ExplicitWaitFactory {
    /**
     * Private constructor
     */
    private ExplicitWaitFactory(){}

    /**
     * A method to find an element and perform implicit wait depending on By locator and waitStrategy
     * @param waitStrategy an enum from {@link org.example.enums.WaitStrategy}
     * @param by a locator for desired element
     * @return a WebElement after implicit wait
     * @see org.example.pages.BasePage
     */
    public static WebElement performExplicitWait(WaitStrategy waitStrategy, By by){

        WebElement element = null;

        if (waitStrategy == WaitStrategy.CLICKABLE){
            element = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(FrameworkConstants.getExplicitWait()))
                    .until(ExpectedConditions.elementToBeClickable(by));
        }
        else if (waitStrategy == WaitStrategy.PRESENCE){
            element = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(FrameworkConstants.getExplicitWait()))
                    .until(ExpectedConditions.presenceOfElementLocated(by));
        }
        else if(waitStrategy == WaitStrategy.VISIBLE){
            element = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(FrameworkConstants.getExplicitWait()))
                    .until(ExpectedConditions.visibilityOfElementLocated(by));
        }
        else if(waitStrategy == WaitStrategy.NONE){
            element = DriverManager.getDriver().findElement(by);
        }
        return element;
    }

    /**
     * A method to find list of elements and perform implicit wait depending on By locator
     * @param by a locator for desired elements
     * @return List of webElements
     */
    public static List<WebElement> performExplicitWaitForList(By by){

        List<WebElement> webElements;

        webElements = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(FrameworkConstants.getExplicitWait()))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));

        return webElements;
    }
}
