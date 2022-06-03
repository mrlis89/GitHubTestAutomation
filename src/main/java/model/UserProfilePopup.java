package model;

import common.Interfaces.Selector;
import common.Page;
import common.selectors.XPathSelector;
import org.openqa.selenium.WebDriver;


public class UserProfilePopup extends Page {
    @Selector(
            selectorName = "Кнопка профиля с фотографией в правом верхнем углу",
            elementXPath = "//header[@role=\"banner\"]/descendant::summary[@aria-label=\"View profile and more\"]"
    )
    private XPathSelector userProfileButton;
    @Selector(
            selectorName = "Кнопка Sign Out",
            elementXPath = "//form[@class=\"logout-form\"]/descendant::button[@type=\"submit\"]"
    )
    private XPathSelector signOutButton;
    @Selector(
            selectorName = "Кнопка Your repositories",
            elementXPath = "//details-menu[@class=\"dropdown-menu dropdown-menu-sw\"]/descendant::a[contains(text(), \"Your repositories\")]"
    )
    private XPathSelector yourRepositoriesButton;

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
