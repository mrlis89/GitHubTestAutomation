package model;

import common.Interfaces.Selector;
import common.Page;
import common.selectors.NameSelector;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;


public class YourRepositoriesPage extends Page {
    @Selector(
            selectorName = "Репозиторий '$Name'",
            elementXPath = "//a[@itemprop=\"name codeRepository\" and contains(text(), '$Name')]"
    )
    private NameSelector repositoryButton;

    public YourRepositoriesPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Нажать на ссылку с именем необходимого репозитория")
    public RepositoryPage openRepository(String repositoryName) {
        repositoryButton.setNameAndClick(repositoryName);
        return new RepositoryPage(webDriver);
    }
}
