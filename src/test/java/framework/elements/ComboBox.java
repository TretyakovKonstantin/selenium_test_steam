package framework.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ComboBox extends BaseElement {

    public ComboBox(By locator) {
        super(locator);
        this.locator = locator;
    }

    public void hoverMouse() {
        Actions action = new Actions(getDriver());
        action.moveToElement(getDriver().findElement(locator)).build().perform();
    }

    public void selectItem(By by) {
        hoverMouse();
        getDriver().findElement(by).click();
    }

    public String getText() {
        return getDriver().findElement(locator).getText();
    }

    public void clickAndSelectItem(By by) {
        getDriver().findElement(locator).click();
        getDriver().findElement(by).click();
    }
}