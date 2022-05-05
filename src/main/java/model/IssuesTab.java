package model;

import common.Issue;
import common.Page;
import common.Selector;
import io.qameta.allure.Step;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class IssuesTab extends Page {
    @Setter
    private Issue issueToOpen;
    private Selector newIssueButton = new Selector(
            webDriver,
            "Кнопка для создания новой задачи",
            "//span[contains(text(),'New issue')]//ancestor::a[@role=\"button\"]"
    );

    public IssuesTab(WebDriver webDriver) {
        super(webDriver);
    }

    public IssueCreationWindow openIssueCreationWindow() {
        newIssueButton.click();
        return new IssueCreationWindow(webDriver);
    }

    @Step("Открыть задачу с именем, написанном при создании")
    public IssueDetailsPage openIssue() {
        WebElement issue = waiter.waitAndInit(By.xpath("//a[@data-hovercard-type=\"issue\" and contains(text(),'" + issueToOpen.getTitle() + "')]"));
        issue.click();
        return new IssueDetailsPage(webDriver);
    }

    @Step("Убедиться что ранее удаленная задача отсутствует в списке задач")
    public void hasNoIssueWithTitle(String issueTitle) throws NoSuchElementException {
        webDriver.findElement(By.xpath("//a[@data-hovercard-type=\"issue\" and contains(text(),'" + issueTitle + "')]"));
    }
}
