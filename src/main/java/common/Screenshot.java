package common;

import io.qameta.allure.Allure;
import lombok.AllArgsConstructor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;

@AllArgsConstructor
public class Screenshot {
    protected WebDriver webDriver;

    public void withName(String name) {
        Allure.addAttachment(name, new ByteArrayInputStream(((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES)));
    }
}
