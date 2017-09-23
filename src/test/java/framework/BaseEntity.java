package framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.TestNG;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import java.util.concurrent.TimeUnit;

public class BaseEntity extends TestNG {
    /*Хранение аннотаций before и after
    Также хранятся проверки и ожидания (wait-ы)
    */

    private WebDriver driver;

    protected final Wait<WebDriver> wait = new WebDriverWait(getDriver(), Integer.parseInt(PropsHelper.getProperty("explicit_wait")));

    @BeforeTest
    public void before(){
        driver = getDriver();
        driver.get(PropsHelper.getProperty("url"));
        int stand_wait = Integer.parseInt(PropsHelper.getProperty("standard_wait"));
        driver.manage().timeouts().implicitlyWait(stand_wait, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(stand_wait, TimeUnit.SECONDS);
    }

    @AfterTest
    public void after(){
        Browser.getInstance().getDriver().close();
    }

    protected WebDriver getDriver() {
        return Browser.getInstance().getDriver();
    }
}
