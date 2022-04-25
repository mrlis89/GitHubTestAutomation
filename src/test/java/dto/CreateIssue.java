package dto;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import model.HomePage;
import model.NavigationPanel;
import model.UserProfilePopup;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;


public class CreateIssue extends BaseTest {

    @Test
    @Description("Пользователь создает новую задачу для указанного репозитория")
    @Feature("Authorization")
    void UserCan() {

        var userProfilePopup = new UserProfilePopup(chromeDriver).open();
        var repos = userProfilePopup.openRepositories();
        var repo = repos.openRepository("forAutotest");
        var issuesTab = repo.openIssuesTab();
        var issueCreation = issuesTab.openIssueCreationWindow();
        issueCreation.enterIssueTitle("test123");
        issueCreation.enterIssueComment("some comment");
        issueCreation.submit();
        repo.openIssuesTab();
        assertThat(issuesTab.containsIssueWithTitle("test123")).isTrue();
    }
}
