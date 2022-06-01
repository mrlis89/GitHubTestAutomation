package model;

import common.Interfaces.SelectorXPath;
import common.Page;
import common.selectors.Selector;
import org.openqa.selenium.WebDriver;


public class UserProfilePopup extends Page {
    @SelectorXPath(
            selectorName = "Кнопка профиля с фотографией в правом верхнем углу",
            elementXPath = "//header[@role=\"banner\"]/descendant::summary[@aria-label=\"View profile and more\"]"
    )
    private Selector userProfileButton;
    @SelectorXPath(
            selectorName = "Кнопка Sign Out",
            elementXPath = "//form[@class=\"logout-form\"]/descendant::button[@type=\"submit\"]"
    )
    private Selector signOutButton;
    @SelectorXPath(
            selectorName = "Кнопка Your repositories",
            elementXPath = "//details-menu[@class=\"dropdown-menu dropdown-menu-sw\"]/descendant::a[contains(text(), \"Your repositories\")]"
    )
    private Selector yourRepositoriesButton;

    public UserProfilePopup(WebDriver webDriver) {
        super(webDriver);
    }

    public UserProfilePopup open() {
        userProfileButton.click();
        return new UserProfilePopup(webDriver);
    }

    public void clickOnSignOut() {
        signOutButton.click();
    }

    public YourRepositoriesPage openRepositories() {
        yourRepositoriesButton.click();
        return new YourRepositoriesPage(webDriver);
    }

    public Boolean isDisplayed() {
        return userProfileButton.isDisplayed();
    }
}
