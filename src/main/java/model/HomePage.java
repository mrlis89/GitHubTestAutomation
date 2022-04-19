package model;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    private final WebDriver webDriver;
    private final String HOME_PAGE_URL = "https://github.com/";

    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Step("Открыть github.com/")
    public void open() {
        webDriver.get(HOME_PAGE_URL);
    }
}
