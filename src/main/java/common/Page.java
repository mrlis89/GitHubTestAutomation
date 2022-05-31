package common;

import org.openqa.selenium.WebDriver;

import static common.selectors.SelectorInitializer.initSelectors;

public class Page {
    protected WebDriver webDriver;
    protected Waiter waiter;

    public Page(WebDriver webDriver) {
        this.webDriver = webDriver;
        waiter = new Waiter(webDriver);
        initSelectors(webDriver,this);
    }
}
