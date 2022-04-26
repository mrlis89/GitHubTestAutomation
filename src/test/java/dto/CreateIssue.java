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
    @Feature("Issues")
    void UserCanCreateNewIssue() {
        //given
        String issueTitle = "test123";
        String issueComment = "some comment";
        var userProfilePopup = new UserProfilePopup(chromeDriver).open();
        var repositoriesPage = userProfilePopup.openRepositories();
        var repo = repositoriesPage.openRepository(userAccount.getRepository());
        var issuesTab = repo.openIssuesTab();
        var issueCreation = issuesTab.openIssueCreationWindow();

        //when
        issueCreation.enterIssueTitle(issueTitle);
        issueCreation.enterIssueComment(issueComment);
        issueCreation.submit();
        var issue = repo.openIssuesTab().openIssueWithTitle(issueTitle);

        //then
        assertThat(issue.containsIssueWithTitle(issueTitle)).isTrue();
        assertThat(issue.containsIssueWithComment(issueComment)).isTrue();
    }
}
