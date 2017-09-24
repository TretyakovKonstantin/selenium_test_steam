package framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseForm extends BaseEntity {

    protected BaseForm() {
    }

    protected List<WebElement> getElements(By by) {
        List<WebElement> elements = getDriver().findElements(by);
        if (elements == null || elements.isEmpty()) {
            Logger.getAnonymousLogger().log(Level.WARNING, "На странице не найдено элементов по локатору " + by);
        }
        return elements;
    }

    protected WebElement getElement(By by) {
        return getDriver().findElement(by);
    }

    public void waitUntilFileDownloads(File file) {
        while (!file.exists()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
