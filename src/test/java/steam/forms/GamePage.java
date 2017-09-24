package steam.forms;

import framework.BaseForm;
import framework.PatternHelper;
import org.openqa.selenium.By;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GamePage extends BaseForm {

    private By discountLocator = By.xpath("//div[@id=\"game_area_purchase\"]//div[@class=\"discount_pct\"]");
    private By priceOriginalLocator = By.xpath("//div[@id=\"game_area_purchase\"]//div[@class=\"discount_original_price\"]");
    private By priceFinalLocator = By.xpath("//div[@id=\"game_area_purchase\"]//div[@class=\"discount_final_price\"]");


    public boolean checkPriceAndDiscount(int[] priceAndDiscount) {
        if (priceAndDiscount.length != 2) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "На вход функции checkPriceAndDiscount был подан неверный массив");
            return false;
        }
        int price = priceAndDiscount[0];
        int discount = priceAndDiscount[1];

        String thisDiscountStr = getElement(discountLocator).getText().replace("-", "").replace("%", "");
        int thisDicount = Integer.parseInt(thisDiscountStr);

        String thisPriceStr = getElement(priceOriginalLocator).getText();
        int thisPrice = PatternHelper.getIntFromString(thisPriceStr);

        return (price == thisPrice && discount == thisDicount);
    }
}
