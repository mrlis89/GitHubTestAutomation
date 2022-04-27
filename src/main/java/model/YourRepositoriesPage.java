package model;

import common.Page;
import common.Screenshot;
import common.Waiter;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class YourRepositoriesPage extends Page {

    public YourRepositoriesPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Нажать на ссылку с именем необходимого репозитория")
    public RepositoryPage openRepository(String repositoryName) {
        By repositoryButtonPath = By.xpath("//a[@itemprop=\"name codeRepository\" and contains(text(), '" + repositoryName + "')]");
        WebElement repositoryButton = waiter.waitAndInit(repositoryButtonPath);
        new Screenshot(webDriver).withName("Страница с репозиториями");
        repositoryButton.click();
        return new RepositoryPage(webDriver);
    }
}
