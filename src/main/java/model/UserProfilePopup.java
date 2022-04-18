package model;

import org.openqa.selenium.By;

import static common.DriverConfigurator.chromeDriver;
import static common.Waiter.waitFor;

public class UserProfilePopup {
    private final By userProfileButton = By.xpath("//header[@role=\"banner\"]/descendant::summary[@aria-label=\"View profile and more\"]");
    private final By signOutButton = By.xpath("//form[@class=\"logout-form\"]/descendant::button[@type=\"submit\"]");

    public UserProfilePopup open() {
        chromeDriver
                .findElement(userProfileButton)
                .click();
        return new UserProfilePopup();
    }

    public void clickOnLogOut() {
        waitFor(signOutButton);
        chromeDriver
                .findElement(signOutButton)
                .click();
    }

    public Boolean isDisplayed() {
        return chromeDriver
                .findElement(userProfileButton).isDisplayed();
    }
}
