package framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class BrowserFactory {
    public static WebDriver setUp(final String type) {
        switch (type) {
            case "firefox":
                FirefoxProfile ffProfile = new FirefoxProfile();

                return new FirefoxDriver(new FirefoxBinary(), ffProfile);

            default:
                return null;
        }
    }
}
