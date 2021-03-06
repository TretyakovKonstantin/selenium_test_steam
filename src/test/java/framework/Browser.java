package framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
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
        String pathRes = PropsHelper.getProperty("rel_path_to_setup");
        pathRes = "\\" + pathRes.substring(0, pathRes.length() - 1);
        System.out.println(pathRes);
        final String dir = System.getProperty("user.dir");
        System.out.println("current dir = " + dir);
        switch (PropsHelper.getProperty("browser")) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver.exe");

                Map<String, Object> prefs = new HashMap<>();
                prefs.put("download.default_directory", dir + pathRes);

                DesiredCapabilities caps = DesiredCapabilities.chrome();
                ChromeOptions options = new ChromeOptions();
                options.setExperimentalOption("prefs", prefs);
                caps.setCapability(ChromeOptions.CAPABILITY, options);

                driver.get("chrome://settings/advanced");
                driver.findElement(By.id("privacyContentSettingsButton")).click();
                driver = new ChromeDriver(caps);
                driver.findElement(By.name("popups")).click();

                break;

            case "firefox":
                System.setProperty("webdriver.gecko.driver", "src\\test\\resources\\geckodriver.exe");
                File pathToBinary = new File("C:\\Users\\treti\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
                FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);

                FirefoxProfile firefoxProfile = new FirefoxProfile();
                //Set Location to store files after downloading.
                firefoxProfile.setPreference("browser.download.dir", dir + pathRes);
                firefoxProfile.setPreference("browser.download.folderList", 2);

                //Set Preference to not show file download confirmation dialogue using MIME types Of different file extension types.
                firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk",
                        "application/octet-stream");

                firefoxProfile.setPreference("browser.download.manager.showWhenStarting", false);
                firefoxProfile.setPreference("pdfjs.disabled", true);

                driver = new FirefoxDriver(ffBinary, firefoxProfile);
                break;
        }
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(PropsHelper.getProperty("standard_wait")), TimeUnit.SECONDS);
    }
}
