package model;

import common.Waiter;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class IssuesTab {
    private final WebDriver webDriver;
    private Waiter waiter;
    @FindBy(xpath = "//span[contains(text(),'New issue')]//ancestor::a[@role=\"button\"]")
    private WebElement newIssueButton;

    public IssuesTab(WebDriver webDriver) {
        this.webDriver = webDriver;
        waiter = new Waiter(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    @Step("Нажать кнопку New Issue чтобы открыть окно для создания новой задачи")
    public IssueCreationWindow openIssueCreationWindow() {
        waiter.waitFor(newIssueButton);
        newIssueButton.click();
        return new IssueCreationWindow(webDriver);
    }

    public boolean containsIssueWithTitle(String issueTitle) {
        WebElement issue = waiter.waitAndInit(By.xpath("//a[@data-hovercard-type=\"issue\" and contains(text(),'" + issueTitle + "')]"));
        return issue.isDisplayed();
    }
}
