package framework.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Label extends BaseElement {
    public Label(By locator) {
        super(locator);
    }

    public void follow() {
        wait.until(ExpectedConditions.visibilityOf(getDriver().findElement(locator)));
        getDriver().findElement(locator).click();
    }
}
