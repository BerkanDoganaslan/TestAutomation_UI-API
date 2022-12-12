package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;


public class Driver {

    private Driver() {

    }

    static WebDriver driver;

    public static WebDriver getDriver() {



        if (driver==null) {
            switch(ConfigReader.getProperty("browser"))
            {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(DesiredCapabilities.setChromeOptions());
                    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
                    break;
                case "firefox" :
                    WebDriverManager.firefoxdriver().setup();
                    driver=new FirefoxDriver();
                    break;
                case "edge" :
                    WebDriverManager.edgedriver().setup();
                    driver=new EdgeDriver();
                    break;


            }
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        }
        return driver;
    }

    public static void closeDriver() {

        if (driver!=null) {
            driver.close();
            driver=null;
        }
    }
}

