package org.example.pages;

import lombok.SneakyThrows;
import org.example.ExplicitWaitFactory;
import org.example.enums.WaitStrategy;
import org.openqa.selenium.By;

public class MainPage extends BasePage {
    private final By profileImageBy = By.xpath("//img[@alt='profile picture']");

    @SneakyThrows
    public boolean isProfileImagePresent() {
        return ExplicitWaitFactory.performExplicitWait(WaitStrategy.PRESENCE, profileImageBy).isDisplayed();
    }
}
