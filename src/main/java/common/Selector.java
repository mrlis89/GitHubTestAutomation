package common;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Selector extends Page {
    private final String selectorName;
    private final String elementPath;
    private WebElement webElement;

    public Selector(WebDriver webDriver, String description, String elementPath) {
        super(webDriver);
        this.selectorName = description;
        this.elementPath = elementPath;
        webElement = waiter.waitAndInit(By.xpath(elementPath));
    }

    @Step("Нажимаем на {selectorName}")
    public void click() {
        webElement.click();
    }

    public void input(String keys) {
        webElement.sendKeys(keys);
    }

}
