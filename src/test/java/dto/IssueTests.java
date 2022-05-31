package dto;

import common.GWT;
import common.Issue;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import model.IssueCreationWindow;
import model.IssuesTab;
import model.RepositoryPage;
import model.UserProfilePopup;
import org.testng.annotations.Test;

import java.util.List;

import static common.Randomizer.generateString;
import static org.assertj.core.api.Assertions.assertThat;

public class IssueTests extends BaseTest {
    private Issue userIssue;

    @Test
    @Feature("Issues")
    @Description("Пользователь создает новую задачу для указанного репозитория")
    void UserCanCreateNewIssue() {
        RepositoryPage userRepository = new RepositoryPage(chromeDriver);
        IssueCreationWindow issueCreation = new IssueCreationWindow(chromeDriver);

        new GWT<Issue>()
                .given("Имя и комментарий для задачи, открыто окно для создания задачи", () -> {
                    var userIssue = Issue.builder()
                            .title(generateString())
                            .comment(generateString())
                            .build();
                    var userProfilePopup = new UserProfilePopup(chromeDriver).open();
                    var repositoriesPage = userProfilePopup.openRepositories();
                    var repositoryPage = repositoriesPage.openRepository(userAccount.getRepository());
                    repositoryPage.openIssuesTab();
                    var issuesTab = new IssuesTab(chromeDriver, userIssue);
                    issuesTab.openIssueCreationWindow();
                    this.userIssue = userIssue;
                    return userIssue;
                }).when("Задача добавлена", (givenIssue) -> {
                    issueCreation.addIssue(givenIssue);
                    userRepository.openIssuesTab();
                    var issuesTab = new IssuesTab(chromeDriver, givenIssue);
                    var issuePage = issuesTab.openIssue();
                    return issuePage.getIssueFromUI();
                }).then("Добавленная задача должна совпадать с исходной");
    }

    @Test
    @Feature("Issues")
    @Description("Пользователь может удалить задачу из списка")
    void UserCanDeleteIssue() {
        RepositoryPage userRepository = new RepositoryPage(chromeDriver);
        var issuesTab = new IssuesTab(chromeDriver, userIssue);
        new GWT<List<String>>()
                .given("Имя задачи для удаления, открыто окно со списком задач", () ->
                {
                    userRepository.openIssuesTab();
                    return issuesTab.getIssueTitleList();
                })
                .when("Открываем задачу и нажимаем кнопку Delete issue", () -> {
                    issuesTab
                            .openIssue()
                            .deleteIssue();
                    userRepository.openIssuesTab();
                    return issuesTab.getIssueTitleList();
                })
                .then("Задача больше не отображается в списке задач", (givenList, listAfterDeleting) ->{
                    assertThat(givenList.size()-1).isEqualTo(listAfterDeleting.size());
                });
    }


}
