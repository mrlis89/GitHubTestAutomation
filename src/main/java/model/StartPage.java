package model;

import org.openqa.selenium.WebDriver;
import webDriver.Initialize;

public class StartPage {
    private WebDriver webDriver = Initialize.webDriver;
    public void open() {
        webDriver.get("https://github.com/");
    }
}
