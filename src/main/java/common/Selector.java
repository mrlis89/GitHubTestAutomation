package common;

import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static common.ScreenshotTaker.takeScreenshot;
import static io.qameta.allure.Allure.step;

@AllArgsConstructor
public class Selector {
    private WebDriver webDriver;
    private String selectorName;
    private String elementPath;

    private WebElement initWebElement() {
        Waiter waiter = new Waiter(webDriver);
        return waiter.waitAndInit(By.xpath(elementPath));
    }

    public void click() {
        step("Нажимаем на " + selectorName, () -> {
            var element = initWebElement();
            takeScreenshot(webDriver, element);
            element.click();
        });
    }

    public void input(String keys) {
        step("Вводим в поле " + selectorName + "следующие данные:" + keys, () -> {
            var element = initWebElement();
            takeScreenshot(webDriver, element);
            element.sendKeys(keys);
        });
    }

}
