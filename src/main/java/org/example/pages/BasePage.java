package org.example.pages;

import org.example.factories.ExplicitWaitFactory;
import org.example.enums.WaitStrategy;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import static org.example.reports.FrameworkLogger.logWithScreenshot;

public class BasePage {
    protected void click(By by, WaitStrategy waitStrategy, String elementName) {

        WebElement element = ExplicitWaitFactory.performExplicitWait(waitStrategy, by);
        element.click();
        logWithScreenshot("Нажатие. " + elementName);
    }

    protected void click(WebElement webElement, String elementName) {
        webElement.click();
        logWithScreenshot("Нажатие. " + elementName);
    }

    protected void sendKeys(By by, String value, WaitStrategy waitStrategy, String elementName) {

        WebElement element = ExplicitWaitFactory.performExplicitWait(waitStrategy, by);
        element.sendKeys(value);
        logWithScreenshot("Ввод. Элемент: \"" + elementName + "\" Значение: \"" + value + "\".");
    }

    protected void sendKeys(By by, Keys key, WaitStrategy waitStrategy, String elementName) {

        WebElement element = ExplicitWaitFactory.performExplicitWait(waitStrategy, by);
        element.sendKeys(key);
        logWithScreenshot("Ввод " + key.name() + " в " + elementName);
    }
}
