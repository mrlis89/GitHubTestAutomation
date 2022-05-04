package model;

import common.Issue;
import common.Page;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class IssueDetailsPage extends Page {
    @FindBy(xpath = "//span[@class=\"js-issue-title markdown-title\"]")
    private WebElement issueTitle;
    @FindBy(xpath = "//td[@class=\"d-block comment-body markdown-body  js-comment-body\"]/descendant::p")
    private WebElement issueComment;
    @FindBy(xpath = "//strong[text()='Delete issue']")
    private WebElement deleteIssueButton;
    @FindBy(xpath = "//button[@type=\"submit\" and @name=\"verify_delete\"]")
    private WebElement submitDeleting;

    public IssueDetailsPage(WebDriver webDriver) {
        super(webDriver);
    }

    public Issue getIssueFromUI() {
        return Issue.builder()
                .title(getTitleFromUI())
                .comment(getCommentFromUI())
                .build();
    }

    @Step("Проверить что имя задачи совпадает с именем, написанном при создании")
    private String getTitleFromUI() {
        waiter.waitFor(issueTitle);
        return issueTitle.getText();
    }

    @Step("Проверить что комментарий задачи совпадает с комментарием, написанном при создании")
    private String getCommentFromUI() {
        waiter.waitFor(issueComment);
        return issueComment.getText();
    }

    @Step
    public void deleteIssue() {
        waiter.waitFor(deleteIssueButton);
        deleteIssueButton.click();
        waiter.waitFor(submitDeleting);
        submitDeleting.click();
    }
}
