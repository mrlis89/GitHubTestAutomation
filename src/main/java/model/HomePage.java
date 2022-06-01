package model;

import common.Page;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class HomePage extends Page {
    private final String HOME_PAGE_URL = "https://github.com/";

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Открыть github.com/")
    public void open() {
        webDriver.get(HOME_PAGE_URL);
    }
}
