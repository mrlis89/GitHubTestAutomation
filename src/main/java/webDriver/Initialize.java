package webDriver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import dto.UserAccount;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;

public class Initialize {
    public static WebDriver webDriver = new ChromeDriver(cookiesInit());

    public static UserAccount userAccount() throws IOException {
        ObjectMapper mapper = new YAMLMapper();
        return mapper.readValue(new File("credentials/gitHubUser.yaml"), UserAccount.class);
    }

    public static ChromeOptions cookiesInit() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=/cookies");
        return options;
    }
}


