package model;

import common.Page;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class IssuesTab extends Page {
    @FindBy(xpath = "//span[contains(text(),'New issue')]//ancestor::a[@role=\"button\"]")
    private WebElement newIssueButton;

    public IssuesTab(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Нажать кнопку New Issue чтобы открыть окно для создания новой задачи")
    public IssueCreationWindow openIssueCreationWindow() {
        waiter.waitFor(newIssueButton);
        newIssueButton.click();
        return new IssueCreationWindow(webDriver);
    }

    @Step("Открыть задачу с именем, написанном при создании")
    public IssueDetailsPage openIssueWithTitle(String issueTitle) {
        WebElement issue = waiter.waitAndInit(By.xpath("//a[@data-hovercard-type=\"issue\" and contains(text(),'" + issueTitle + "')]"));
        issue.click();
        return new IssueDetailsPage(webDriver);
    }

    @Step("Убедиться что ранее удаленная задача отсутствует в списке задач")
    public void hasNoIssueWithTitle(String issueTitle) throws NoSuchElementException {
        webDriver.findElement(By.xpath("//a[@data-hovercard-type=\"issue\" and contains(text(),'" + issueTitle + "')]"));
    }
}
