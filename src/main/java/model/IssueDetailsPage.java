package model;

import common.Interfaces.Selector;
import common.Issue;
import common.Page;
import common.selectors.BaseSelector;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class IssueDetailsPage extends Page {
    @Selector(
            selectorName = "Заголовок задачи",
            elementXPath = "//span[@class=\"js-issue-title markdown-title\"]"
    )
    private BaseSelector issueTitle;
    @Selector(
            selectorName = "Комментарий задачи",
            elementXPath = "//td[@class=\"d-block comment-body markdown-body  js-comment-body\"]/descendant::p"
    )
    private BaseSelector issueComment;
    @Selector(
            selectorName = "Удалить задачу",
            elementXPath = "//strong[text()='Delete issue']"
    )
    private BaseSelector deleteIssueButton;
    @Selector(
            selectorName = "Подтвердить удаление",
            elementXPath = "//button[@type=\"submit\" and @name=\"verify_delete\"]"
    )
    private BaseSelector submitDeleting;

    public IssueDetailsPage(WebDriver webDriver) {
        super(webDriver);
    }

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
