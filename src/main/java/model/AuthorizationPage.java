package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import webDriver.Initialize;

import java.time.Duration;

public class AuthorizationPage {
    private WebDriver webDriver = Initialize.webDriver;

    public void enterLogin(String login) {
        new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//input[@id=\"login_field\"]")
                        )
                );
        WebElement userLogin = webDriver.findElement(By.xpath("//input[@id=\"login_field\"]"));
        userLogin.sendKeys(login);
    }

    public void enterPassword(String password) {
        WebElement userPassword = webDriver.findElement(By.xpath("//input[@id=\"password\"]"));
        userPassword.sendKeys(password);
    }

    public void clickSignIn() {
        WebElement signInBtn = webDriver.findElement(By.xpath("//input[@value=\"Sign in\"]"));
        signInBtn.click();
    }
}
