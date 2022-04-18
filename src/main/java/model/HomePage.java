package model;

import static common.DriverConfigurator.chromeDriver;

public class HomePage {
    private final String HOME_PAGE_URL = "https://github.com/";

    public void open() {
        chromeDriver.get(HOME_PAGE_URL);
    }
}
