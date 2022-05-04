package dto;

import common.GWT;
import common.Issue;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import model.IssueCreationWindow;
import model.IssuesTab;
import model.RepositoryPage;
import model.UserProfilePopup;
import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.Test;

import static common.Randomizer.generateString;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class IssueTests extends BaseTest {
    private Issue userIssue;

    @Test
    @Feature("Issues")
    @Description("Пользователь создает новую задачу для указанного репозитория")
    void UserCanCreateNewIssue() {
        RepositoryPage userRepository = new RepositoryPage(chromeDriver);
        IssueCreationWindow issueCreation = new IssueCreationWindow(chromeDriver);

        new GWT<Issue>(chromeDriver)
                .given("Имя и комментарий для задачи, открыто окно для создания задачи", () -> {
                    var userIssue = Issue.builder()
                            .title(generateString())
                            .comment(generateString())
                            .build();
                    var userProfilePopup = new UserProfilePopup(chromeDriver).open();
                    var repositoriesPage = userProfilePopup.openRepositories();
                    var repositoryPage = repositoriesPage.openRepository(userAccount.getRepository());
                    var issuesTab = repositoryPage.openIssuesTab();
                    issuesTab.openIssueCreationWindow();
                    this.userIssue = userIssue;
                    return userIssue;
                }).when("Задача добавлена", (givenIssue) -> {
                    issueCreation.addIssue(givenIssue);
                    var issuePage = userRepository
                            .openIssuesTab()
                            .openIssueWithTitle(givenIssue.getTitle());
                    return issuePage.getIssueFromUI();
                }).then("Добавленная задача должна совпадать с исходной");
    }

    @Test
    @Feature("Issues")
    @Description("Пользователь может удалить задачу из списка")
    void UserCanDeleteIssue() {
        RepositoryPage userRepository = new RepositoryPage(chromeDriver);
        new GWT<IssuesTab>(chromeDriver)
                .given("Имя задачи для удаления, открыто окно со списком задач", () ->
                        userRepository
                                .openIssuesTab())
                .when("Открываем задачу и нажимаем кнопку Delete issue", (issueTab) -> {
                    issueTab
                            .openIssueWithTitle(userIssue.getTitle())
                            .deleteIssue();
                    return issueTab;

                })
                .then("Задача больше не отображается в списке задач", (issuesTab) ->
                        assertThatThrownBy(
                                () ->issuesTab.hasNoIssueWithTitle(userIssue.getTitle())
                        ).isInstanceOf(NoSuchElementException.class)
                );
    }


}
