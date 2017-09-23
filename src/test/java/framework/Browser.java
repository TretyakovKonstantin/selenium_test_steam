package framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class Browser {
    private static Browser ourInstance = new Browser();

    public static Browser getInstance() {
        return ourInstance;
    }

    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public void exit() {
        driver.close();
    }

    private Browser() {
        switch (PropsHelper.getProperty("browser")) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver.exe");
                driver = new ChromeDriver();
                break;

            case "firefox":
                System.setProperty("webdriver.gecko.driver", "src\\test\\resources\\geckodriver.exe");
                File pathToBinary = new File("C:\\Users\\treti\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
                FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
                FirefoxProfile firefoxProfile = new FirefoxProfile();
                driver = new FirefoxDriver(ffBinary, firefoxProfile);
                break;

            case "edge":
                System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\MicrosoftWebDriver.exe");
                driver = new ChromeDriver();
                break;

        }
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(PropsHelper.getProperty("standard_wait")), TimeUnit.SECONDS);
    }
}
