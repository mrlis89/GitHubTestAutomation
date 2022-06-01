package common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waiter {
    private final WebDriver webDriver;

    public Waiter(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void waitFor(WebElement pageElement) {
        new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(pageElement));
    }
    public void waitFor(By pageElement) {
        new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(pageElement));
    }

    public WebElement waitAndInit(By pageElementPath) {
        return new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(pageElementPath));
    }
}
