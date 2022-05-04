package common;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;

public class ScreenshotTaker {
    private static ScreenshotTaker INSTANCE;

    private ScreenshotTaker() {
    }

    public static ScreenshotTaker getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new ScreenshotTaker();
        }
        return INSTANCE;
    }

    public static void takeScreenshot(WebDriver webDriver) {
        Allure.addAttachment("Screenshot", new ByteArrayInputStream(((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES)));
    }
}
