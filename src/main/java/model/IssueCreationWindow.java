package model;

import common.Interfaces.Selector;
import common.Issue;
import common.Page;
import common.selectors.XPathSelector;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class IssueCreationWindow extends Page {
    @Selector(
            selectorName = "Submit new issue",
            elementXPath = "//button[contains(text(),'Submit new issue') and @class=\"btn-primary btn\"]"
    )
    private XPathSelector submitNewIssueButton;
    @Selector(
            selectorName = "Поле ввода заголовка задачи",
            elementXPath = "//input[@id=\"issue_title\"]"
    )
    private XPathSelector issueTitleField;
    @Selector(
            selectorName = "Поле ввода комментария для задачи",
            elementXPath = "//textarea[@id=\"issue_body\"]"
    )
    private XPathSelector issueCommentField;

    public IssueCreationWindow(WebDriver webDriver) {
        super(webDriver);
    }

    public void enterIssueTitle(String issueName) {
        issueTitleField.input(issueName);
    }

    public void enterIssueComment(String issueComment) {
        issueCommentField.input(issueComment);
    }

    public void submit() {
        submitNewIssueButton.click();
    }

    @Step("Добавить новую задачу, введя для нее имя и комментарий")
    public void addIssue(Issue issueToAdd) {
        enterIssueTitle(issueToAdd.getTitle());
        enterIssueComment(issueToAdd.getComment());
        submit();
    }
}
