package model;

import common.Waiter;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePageAuthorized {
    private final WebDriver webDriver;
    private Waiter waiter;
    private final String HOME_PAGE_URL = "https://github.com/";
    @FindBy(xpath = "//a[@itemprop=\"name codeRepository\" and contains(text(), 'Interpreter')]")
    private WebElement yourRepositoriesButton;

    public HomePageAuthorized(WebDriver webDriver) {
        this.webDriver = webDriver;
        waiter = new Waiter(webDriver);
        PageFactory.initElements(webDriver, this);
    }

}
