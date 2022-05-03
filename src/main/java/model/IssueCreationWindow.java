package model;

import common.Issue;
import common.Page;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class IssueCreationWindow extends Page {
    @FindBy(xpath = "//button[contains(text(),'Submit new issue') and @class=\"btn-primary btn\"]")
    private WebElement submitNewIssueButton;

    public IssueCreationWindow(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Ввести заголовок для новой задачи")
    public void enterIssueTitle(String issueName) {
        By issueTitleFieldPath = By.xpath("//input[@id=\"issue_title\"]");
        WebElement issueTitleField = waiter.waitAndInit(issueTitleFieldPath);
        issueTitleField.sendKeys(issueName);
    }

    @Step("Ввести комментарий для новой задачи")
    public void enterIssueComment(String issueComment) {
        By issueCommentFieldPath = By.xpath("//textarea[@id=\"issue_body\"]");
        WebElement issueCommentField = waiter.waitAndInit(issueCommentFieldPath);
        issueCommentField.sendKeys(issueComment);
    }

    @Step("Нажать кнопку Submit new issue чтобы завершить создание новой задачи")
    public void submit() {
        waiter.waitFor(submitNewIssueButton);
        submitNewIssueButton.click();
    }

    @Step("Добавить новую задачу, введя для нее имя и комментарий")
    public IssueCreationWindow addIssue(Issue issueToAdd) {
        enterIssueTitle(issueToAdd.getTitle());
        enterIssueComment(issueToAdd.getComment());
        submit();
        return new IssueCreationWindow(webDriver);
    }
}
