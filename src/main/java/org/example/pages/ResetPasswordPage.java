package org.example.pages;

import lombok.SneakyThrows;
import org.example.driver.DriverManager;

public class ResetPasswordPage extends BasePage {
    @SneakyThrows
    public String getResetPasswordUrl() {
        return DriverManager.getDriver().getCurrentUrl();
    }
}
