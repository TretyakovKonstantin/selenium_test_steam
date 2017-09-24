package steam.forms;

import framework.BaseForm;
import framework.PatternHelper;
import framework.PropsHelper;
import framework.elements.Tab;
import framework.exceptions.NotAllElementsHasBeenFoundException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ActionsPage extends BaseForm {
    private WebDriver driver;
    private Tab currentTab;

    private String tabLocator = "//div[@class=\"tab_content\" and contains(., \"%s\")]";
    private String discountsLocator = "//div[@id=\"DiscountsRows\"]//div[@class=\"discount_pct\"]\n";
    private String pricesLocator = "//div[@id=\"DiscountsRows\"]//div[@class=\"discount_original_price\"]";

    public ActionsPage(WebDriver driver) {
        super();
        this.driver = driver;
        By popularNew = By.xpath(String.format(tabLocator, "Популярные новинки"));
        currentTab = new Tab(popularNew);
    }

    public void changeTab(String newTab) {
        By discountLoc = By.xpath(String.format(tabLocator, newTab));
        currentTab = new Tab(discountLoc);
        currentTab.goToTab();
    }

    public int[] getTheBestDiscount() throws NotAllElementsHasBeenFoundException, InterruptedException {
        By discountsBy = By.xpath(discountsLocator);
        By pricesBy = By.xpath(pricesLocator);
        wait.until(ExpectedConditions.stalenessOf(getElements(discountsBy).get(getElements(discountsBy).size() - 1)));
        List<WebElement> discounts = getElements(discountsBy);
        List<WebElement> prices = getElements(pricesBy);

        String[] discountsInNums = new String[discounts.size()];
        for (int i = 0; i < discountsInNums.length; i++) {
            discountsInNums[i] = discounts.get(i).getText().replace("-", "").replace("%", "");
        }

        int maxDiscount = 0;
        int maxDiscountIndex = 0;
        for (int i = 0; i < discountsInNums.length; i++) {
            maxDiscount = Integer.max(maxDiscount, Integer.parseInt(discountsInNums[i]));
            if (maxDiscount < Integer.parseInt(discountsInNums[i])) {
                maxDiscount = Integer.parseInt(discountsInNums[i]);
                maxDiscountIndex = i;
            }
        }
        String priceStr = prices.get(maxDiscountIndex).getText().replaceAll(",", ".");

        int price = PatternHelper.getIntFromString(priceStr);

        discounts.get(maxDiscountIndex).click();
        return new int[]{price, maxDiscount};
    }

    public GamePage confirmAgeIfNeeded(String strYear, String month, String strDay) {
        if (driver.getCurrentUrl().contains("agecheck")) {
            int year = Integer.parseInt(strYear);
            int day = Integer.parseInt(strDay);
            AgeCheckPage ageCheckPage = new AgeCheckPage();
            ageCheckPage.confirmAge(day, month, year);
        }
        return new GamePage();
    }


}
