package framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseForm extends BaseEntity {

    protected BaseForm(){
    }

    public List<WebElement> getElements(By by) {
        List<WebElement> elements = Browser.getInstance().getDriver().findElements(by);
        if (elements == null || elements.isEmpty()) {
            Logger.getAnonymousLogger().log(Level.WARNING, "На странице не найдено элементов по локатору " + by);
        }
        return elements;
    }


}
