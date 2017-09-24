package framework.elements;

import framework.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class Tab extends BaseElement {

    public Tab(By locator) {
        super(locator);
    }

    public void goToTab() {
        getDriver().findElement(locator).click();
    }
}
