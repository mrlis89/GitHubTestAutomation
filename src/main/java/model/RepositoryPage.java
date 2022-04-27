package model;

import common.Page;
import common.Screenshot;
import common.Waiter;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class RepositoryPage extends Page {
    @FindBy(id = "issues-tab")
    private WebElement issueTab;

    public RepositoryPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Нажать кнопку Issues чтобы открыть окно с задачами по проекту")
    public IssuesTab openIssuesTab() {
        waiter.waitFor(issueTab);
        new Screenshot(webDriver).withName("Страница указанного репозитория");
        issueTab.click();
        return new IssuesTab(webDriver);
    }
}
