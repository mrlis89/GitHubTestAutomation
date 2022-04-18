package common;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverConfigurator {
    public static WebDriver chromeDriver = new ChromeDriver(cookiesInit());
    private static final String USER_COOKIES_PATH = "/cookies";

    public static ChromeOptions cookiesInit() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=" + USER_COOKIES_PATH);
        return options;
    }

    public static void setPageSize(int width, int height) {
        chromeDriver.manage().window().setSize(new Dimension(width, height));
    }
}


