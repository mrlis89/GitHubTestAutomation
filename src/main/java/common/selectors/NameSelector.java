package common.selectors;

import org.openqa.selenium.WebDriver;

import static common.ScreenshotTaker.takeScreenshot;
import static io.qameta.allure.Allure.step;

public class NameSelector extends SelectorBase {

    public NameSelector(WebDriver webDriver, String selectorName, String elementPath) {
        super(webDriver, selectorName, elementPath);
    }

    public void setNameAndClick(String name) {
        selectorName = selectorName.replace("$Name", name);
        elementPath = elementPath.replace("$Name", name);
        step("Нажимаем на: " + selectorName, () -> {
            var element = initWebElement();
            takeScreenshot(webDriver, element);
            element.click();
        });
    }
}
