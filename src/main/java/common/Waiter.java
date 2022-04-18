package common;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static common.DriverConfigurator.chromeDriver;

public class Waiter {
    private static final int DEFAULT_TIME_OUT = 10;

    public static void waitFor(By pageElement) {
        new WebDriverWait(chromeDriver, Duration.ofSeconds(DEFAULT_TIME_OUT))
                .until(ExpectedConditions.elementToBeClickable(pageElement)
                );
    }
}
