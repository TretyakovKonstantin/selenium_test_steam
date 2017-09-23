package framework.elements;

import framework.BaseEntity;
import framework.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BaseElement extends BaseEntity{
    /*Обертки над selenium методами работы с элементами
      Если в работе используется фреймворк, то на уровне
      теста нельзя обращаться к нативным методам selenium
      (только через обертку во фреймворке)
      Обертку нужно делать проверку.
      Использовать метод clickAndWait когда selenium
      загружает следующую страницу

      Загуглить, как selenium webdriver работает с AJAX -
      какие элементы нужно интегрировать, т.к. 3 задание - работа со
      страницами с автоматически подгружаемым контентом
     */

    protected By locator;

    public BaseElement(By locator){
        this.locator = locator;
    }

    public void waitAndClick() {
        WebElement element = getDriver().findElement(locator);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }
}
