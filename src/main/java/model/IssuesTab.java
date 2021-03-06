package model;

import common.Interfaces.Selector;
import common.Issue;
import common.Page;
import common.selectors.XPathSelector;
import common.selectors.ListSelector;
import common.selectors.NameSelector;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

public class IssuesTab extends Page {

    private final Issue issueToOpen;

    @Selector(
            selectorName = "Кнопка для создания новой задачи",
            elementXPath = "//span[contains(text(),'New issue')]//ancestor::a[@role=\"button\"]"
    )
    private XPathSelector newIssueButton;

    @Selector(
            selectorName = "Задача с именем $Name",
            elementXPath = "//a[@data-hovercard-type=\"issue\" and contains(text(),'$Name')]"
    )
    private NameSelector issueSelector;

    @Selector(
            selectorName = "Список задач",
            elementXPath = "//a[@data-hovercard-type=\"issue\"]"
    )
    private ListSelector issueList;

    public IssuesTab(WebDriver webDriver, Issue issueToOpen) {
        super(webDriver);
        this.issueToOpen = issueToOpen;
    }

    public IssueCreationWindow openIssueCreationWindow() {
        newIssueButton.click();
        return new IssueCreationWindow(webDriver);
    }

    public IssueDetailsPage openIssue() {
        issueSelector.setNameAndClick(issueToOpen.getTitle());
        return new IssueDetailsPage(webDriver);
    }
    public ArrayList<String> getIssueTitleList() {
        return issueList.getList();
    }
}
