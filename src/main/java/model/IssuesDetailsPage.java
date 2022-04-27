package model;

import common.Issue;
import common.Page;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class IssuesDetailsPage extends Page {
    @FindBy(xpath = "//span[@class=\"js-issue-title markdown-title\"]")
    private WebElement issueTitle;
    @FindBy(xpath = "//td[@class=\"d-block comment-body markdown-body  js-comment-body\"]/descendant::p")
    private WebElement issueComment;

    public IssuesDetailsPage(WebDriver webDriver) {
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
}
