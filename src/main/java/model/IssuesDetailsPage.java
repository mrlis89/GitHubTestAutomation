package model;

import common.Page;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class IssuesDetailsPage extends Page {
    @FindBy(xpath = "//span[contains(text(),'New issue')]//ancestor::a[@role=\"button\"]")
    private WebElement newIssueButton;

    public IssuesDetailsPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Нажать кнопку New Issue чтобы открыть окно для создания новой задачи")
    public IssueCreationWindow openIssueCreationWindow() {
        waiter.waitFor(newIssueButton);
        newIssueButton.click();
        return new IssueCreationWindow(webDriver);
    }

    public boolean containsIssueWithTitle(String issueTitle) {
        WebElement issue = waiter.waitAndInit(By.xpath("//span[@class=\"js-issue-title markdown-title\" and contains(text(),'" + issueTitle + "')]"));
        return issue.isDisplayed();
    }

    public boolean containsIssueWithComment(String issueComment) {
        WebElement issue = waiter.waitAndInit(By.xpath(
                        "//td[@class=\"d-block comment-body markdown-body  js-comment-body\"]/descendant::p[contains(text(),'" + issueComment + "')]"
                )
        );
        return issue.isDisplayed();
    }
}
