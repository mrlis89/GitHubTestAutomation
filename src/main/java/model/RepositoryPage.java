package model;

import common.Page;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class RepositoryPage extends Page {
    @FindBy(id = "issues-tab")
    private WebElement issueTab;

    public RepositoryPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Нажать кнопку Issues чтобы открыть окно с задачами по проекту")
    public IssuesTab openIssuesTab() {
        waiter.waitFor(issueTab);
        issueTab.click();
        return new IssuesTab(webDriver);
    }
}
