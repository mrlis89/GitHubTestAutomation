package common.selectors;

import common.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static common.ScreenshotTaker.takeScreenshot;
import static io.qameta.allure.Allure.step;

public class ListSelector extends XPathSelector {

    public ListSelector(WebDriver webDriver, String selectorName, String elementPath) {
        super(webDriver, selectorName, elementPath);
    }

    public ArrayList<String> getList() {
        var elementTitles = new ArrayList<String>();
        step("Получаем : " + selectorName, () -> {
            var elements = initWebElements();
            elements.forEach((it) -> {
                elementTitles.add(it.getText());
            });
            takeScreenshot(webDriver);
        });
        return elementTitles;
    }

    protected List<WebElement> initWebElements() {
        Waiter waiter = new Waiter(webDriver);
        waiter.waitFor(By.xpath(elementPath));
        return webDriver.findElements(By.xpath(elementPath));
    }

}
