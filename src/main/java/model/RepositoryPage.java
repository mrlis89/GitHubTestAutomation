package model;

import common.Interfaces.SelectorID;
import common.Page;
import common.selectors.IDSelector;
import org.openqa.selenium.WebDriver;


public class RepositoryPage extends Page {
    @SelectorID(
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
