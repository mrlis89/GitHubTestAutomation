package common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static common.selectors.SelectorInitializer.initSelectors;

public class Page {
    protected WebDriver webDriver;
    protected Waiter waiter;

    public Page(WebDriver webDriver) {
        this.webDriver = webDriver;
        waiter = new Waiter(webDriver);
        PageFactory.initElements(webDriver, this);
        initSelectors(webDriver,this);
    }
}
