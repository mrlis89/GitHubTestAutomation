package model;

import common.*;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

public class IssuesTab extends Page {
    private final Issue issueToOpen;
    private Selector newIssueButton = new Selector(
            webDriver,
            "Кнопка для создания новой задачи",
            "//span[contains(text(),'New issue')]//ancestor::a[@role=\"button\"]"
    );
    private NameSelector issueSelector = new NameSelector(
            webDriver,
            "Задача с именем $Name",
            "//a[@data-hovercard-type=\"issue\" and contains(text(),'$Name')]"
    );
    private ListSelector issueList = new ListSelector(
            webDriver,
            "Список задач",
            "//a[@data-hovercard-type=\"issue\"]"
    );

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
