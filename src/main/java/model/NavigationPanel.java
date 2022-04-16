package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import webDriver.Initialize;

import java.time.Duration;

public class NavigationPanel {
    private WebDriver webDriver = Initialize.webDriver;

    public NavigationPanel open() {
        WebElement NavigationBtn = webDriver
                .findElement(By.xpath("//button[@aria-label=\"Toggle navigation\"]"));
        NavigationBtn.click();
        return new NavigationPanel();
    }

    public AuthorizationPage clickOnSignIn() {
        new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//a[@href=\"/login\"]")
                        )
                );
        WebElement signInBtn = webDriver.findElement(By.xpath("//a[@href=\"/login\"]"));
        signInBtn.click();
        return new AuthorizationPage();
    }
}
