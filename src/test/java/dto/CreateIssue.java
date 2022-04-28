package dto;

import common.Issue;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import model.UserProfilePopup;
import org.testng.annotations.*;

import static common.Randomizer.generateString;
import static org.assertj.core.api.Assertions.assertThat;


public class CreateIssue extends BaseTest {

    @Test
    @Description("Пользователь создает новую задачу для указанного репозитория")
    @Feature("Issues")
    void UserCanCreateNewIssue() {
        //given
        var userIssue = Issue.builder()
                .title(generateString())
                .comment(generateString())
                .build();
        var userProfilePopup = new UserProfilePopup(chromeDriver).open();
        var repositoriesPage = userProfilePopup.openRepositories();
        var repo = repositoriesPage.openRepository(userAccount.getRepository());
        var issuesTab = repo.openIssuesTab();
        var issueCreation = issuesTab.openIssueCreationWindow();

        //when
        issueCreation.addIssue(userIssue);
        var issuePage = repo
                .openIssuesTab()
                .openIssueWithTitle(userIssue.getTitle());
        var issueFromUI = issuePage.getIssueFromUI();

        //then
        assertThat(userIssue).isEqualTo(issueFromUI);
    }
}
