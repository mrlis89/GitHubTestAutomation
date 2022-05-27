package model;

import common.Interfaces.Selector;
import common.Issue;
import common.Page;
import common.selectors.ListSelector;
import common.selectors.NameSelector;
import common.selectors.SelectorBase;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

import static common.selectors.SelectorInitializer.initSelectors;

public class IssuesTab extends Page {

    private final Issue issueToOpen;

    @Selector(
            selectorName = "Кнопка для создания новой задачи",
            elementPath = "//span[contains(text(),'New issue')]//ancestor::a[@role=\"button\"]"
    )
    private SelectorBase newIssueButton;

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
        initSelectors(webDriver,this);
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
