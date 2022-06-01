package model;

import common.Interfaces.SelectorXPath;
import common.Issue;
import common.Page;
import common.selectors.Selector;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class IssueDetailsPage extends Page {
    @SelectorXPath(
            selectorName = "Заголовок задачи",
            elementXPath = "//span[@class=\"js-issue-title markdown-title\"]"
    )
    private Selector issueTitle;
    @SelectorXPath(
            selectorName = "Комментарий задачи",
            elementXPath = "//td[@class=\"d-block comment-body markdown-body  js-comment-body\"]/descendant::p"
    )
    private Selector issueComment;
    @SelectorXPath(
            selectorName = "Удалить задачу",
            elementXPath = "//strong[text()='Delete issue']"
    )
    private Selector deleteIssueButton;
    @SelectorXPath(
            selectorName = "Подтвердить удаление",
            elementXPath = "//button[@type=\"submit\" and @name=\"verify_delete\"]"
    )
    private Selector submitDeleting;

    public IssueDetailsPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Получить детали задачи")
    public Issue getIssueFromUI() {
        return Issue.builder()
                .title(getTitleFromUI())
                .comment(getCommentFromUI())
                .build();
    }

    private String getTitleFromUI() {
        return issueTitle.getText();
    }

    private String getCommentFromUI() {
        return issueComment.getText();
    }

    @Step("Удалить задачу с заданным именем")
    public void deleteIssue() {
        deleteIssueButton.click();
        submitDeleting.click();
    }
}
