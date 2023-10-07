package org.example.pages;

import lombok.SneakyThrows;
import org.example.factories.ExplicitWaitFactory;
import org.example.enums.WaitStrategy;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {
    private final By loginInput = By.xpath("//input[@name='username']");
    private final By passwordInput = By.xpath("//input[@name='password']");
    private final By loginBtn = By.xpath("//button[@type='submit']");
    private final By forgetPassLink = By.xpath("//p[contains(.,'Forgot your password?')]");
    private final String errorMessage = "//div[@class='orangehrm-login-error']//p[.='%s']";

    public LoginPage enterLogin(String login) {
        sendKeys(loginInput, login, WaitStrategy.PRESENCE, "Логин");
        return this;
    }

    public LoginPage enterPassword(String password) {
        sendKeys(passwordInput, password, WaitStrategy.PRESENCE, "Пароль");
        return this;
    }

    @SneakyThrows
    public void clickLoginBtn() {
        click(loginBtn, WaitStrategy.CLICKABLE, "Кнопка login");
        Thread.sleep(2000);
    }

    public void clickForgetPassLink() {
        click(forgetPassLink, WaitStrategy.CLICKABLE, "Ссылка 'Forgot your password?'");
    }

    public boolean isErrorPresent(String errorName) {
        By errorMessageBy = By.xpath(String.format(errorMessage, errorName));
        return ExplicitWaitFactory.performExplicitWait(WaitStrategy.PRESENCE, errorMessageBy).isDisplayed();
    }
}
