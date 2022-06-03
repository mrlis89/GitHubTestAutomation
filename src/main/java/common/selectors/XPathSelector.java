package common.selectors;

import common.Waiter;
import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static common.ScreenshotTaker.takeScreenshot;
import static io.qameta.allure.Allure.step;

@AllArgsConstructor
public class XPathSelector {
    protected WebDriver webDriver;
    protected String selectorName;
    protected String elementPath;

    public void click() {
        step("Нажимаем на: " + selectorName, () -> {
            var element = initWebElement();
            focusTo(element);
            takeScreenshot(webDriver, element);
            element.click();
        });
    }

    public String getText() {
        var element = initWebElement();
        focusTo(element);
        step("Прочитать: " + selectorName, () -> {
            takeScreenshot(webDriver, element);
        });
        return element.getText();
    }

    public void input(String keys) {
        step("Вводим в: " + selectorName + " следующие данные: " + keys, () -> {
            var element = initWebElement();
            focusTo(element);
            element.sendKeys(keys);
            takeScreenshot(webDriver, element);
        });
    }

    protected WebElement initWebElement() {
        Waiter waiter = new Waiter(webDriver);
        return waiter.waitAndInit(By.xpath(elementPath));
    }

    public Boolean isDisplayed() {
        Waiter waiter = new Waiter(webDriver);
        return waiter
                .waitAndInit(By.xpath(elementPath))
                .isDisplayed();
    }

    private void focusTo(WebElement element) {
        new Actions(webDriver).moveToElement(element).perform();
    }

}
