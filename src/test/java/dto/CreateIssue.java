package dto;

import common.GWT;
import common.Issue;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import model.IssueCreationWindow;
import model.RepositoryPage;
import model.UserProfilePopup;
import org.testng.annotations.*;

import static common.Randomizer.generateString;

public class CreateIssue extends BaseTest {

    @Test
    @Feature("Issues")
    @Description("Пользователь создает новую задачу для указанного репозитория")
    void UserCanCreateNewIssue() {
        RepositoryPage repo = new RepositoryPage(chromeDriver);
        IssueCreationWindow issueCreation = new IssueCreationWindow(chromeDriver);

        new GWT<Issue>().given("Имя и комментарий для задачи, открыто окно для создания задачи", () -> {
            var userIssue = Issue.builder()
                    .title(generateString())
                    .comment(generateString())
                    .build();
            var userProfilePopup = new UserProfilePopup(chromeDriver).open();
            var repositoriesPage = userProfilePopup.openRepositories();
            var repositoryPage = repositoriesPage.openRepository(userAccount.getRepository());
            var issuesTab = repositoryPage.openIssuesTab();
            issuesTab.openIssueCreationWindow();
            return userIssue;
        }).when("Задача добавлена", (given) -> {
            issueCreation.addIssue(given);
            var issuePage = repo
                    .openIssuesTab()
                    .openIssueWithTitle(given.getTitle());
            return issuePage.getIssueFromUI();
        }).then("Добавленная задача должна совпадать с исходной");

    }
}
