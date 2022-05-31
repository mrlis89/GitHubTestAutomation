package common.selectors;

import common.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class IDSelector extends Selector {
    public IDSelector(WebDriver webDriver, String selectorName, String elementID) {
        super(webDriver, selectorName, elementID);
    }

    @Override
    protected WebElement initWebElement() {
        Waiter waiter = new Waiter(webDriver);
        return waiter.waitAndInit(By.id(elementPath));
    }
}
