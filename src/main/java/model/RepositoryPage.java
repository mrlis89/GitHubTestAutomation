package model;

import common.Interfaces.Selector;
import common.Page;
import common.selectors.IDSelector;
import org.openqa.selenium.WebDriver;


public class RepositoryPage extends Page {
    @Selector(
            selectorName = "Кнопка Issues",
            elementID = "issues-tab"
    )
    private IDSelector issueTab;

    public RepositoryPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openIssuesTab() {
        issueTab.click();
    }
}
