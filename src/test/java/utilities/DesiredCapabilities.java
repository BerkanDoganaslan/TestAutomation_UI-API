package utilities;

import org.openqa.selenium.chrome.ChromeOptions;

public class DesiredCapabilities {
    static ChromeOptions options = new ChromeOptions();

    public static ChromeOptions setChromeOptions() {

        options.addArguments("start-maximized");
        options.addArguments("disable-extensions");
        options.addArguments("disable-popup-blocking");
        options.addArguments("--disable-notifications");
        options.addArguments("disable-infobars");

        return options;
    }

}