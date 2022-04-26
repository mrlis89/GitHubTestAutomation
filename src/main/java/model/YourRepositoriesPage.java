package model;

import common.Page;
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

    @Step("Нажать кнопку Issues чтобы открыть окно с задачами по проекиу")
    public RepositoryPage openRepository(String repositoryName) {
        By repositoryButtonPath = By.xpath("//a[@itemprop=\"name codeRepository\" and contains(text(), '" + repositoryName + "')]");
        WebElement repositoryButton = waiter.waitAndInit(repositoryButtonPath);
        repositoryButton.click();
        return new RepositoryPage(webDriver);
    }
}
