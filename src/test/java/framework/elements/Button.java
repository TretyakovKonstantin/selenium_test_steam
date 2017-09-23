package framework.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Button extends BaseElement {
    public Button(By locator) {
        super(locator);
    }

    public void confirm(){
        wait.until(ExpectedConditions.elementToBeClickable(getDriver().findElement(locator)));
        getDriver().findElement(locator).click();
    }
}
