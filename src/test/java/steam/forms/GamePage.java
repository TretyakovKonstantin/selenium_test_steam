package steam.forms;

import framework.BaseForm;
import framework.PatternHelper;
import framework.PropsHelper;
import framework.elements.Button;
import org.openqa.selenium.By;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GamePage extends BaseForm {

    private By discountLocator = By.xpath("//div[@id=\"game_area_purchase\"]//div[@class=\"discount_pct\"]");
    private By priceOriginalLocator = By.xpath("//div[@id=\"game_area_purchase\"]//div[@class=\"discount_original_price\"]");
    private By priceFinalLocator = By.xpath("//div[@id=\"game_area_purchase\"]//div[@class=\"discount_final_price\"]");

    private By installSteamLocator = By.linkText(PropsHelper.getLocProperty("install_steam"));


    public boolean checkPriceAndDiscount(double[] priceAndDiscount) {
        if (priceAndDiscount.length != 2) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "На вход функции checkPriceAndDiscount был подан неверный массив");
            return false;
        }
        double originPrice = priceAndDiscount[0];
        int discount = (int)priceAndDiscount[1];

        String thisDiscountStr = getElement(discountLocator).getText().replace("-", "").replace("%", "");
        int thisDicount = Integer.parseInt(thisDiscountStr);

        String thisFinalPriceStr = getElement(priceFinalLocator).getText();
        double thisFinalPrice = PatternHelper.getIntFromString(thisFinalPriceStr);

        double thisOriginPrice = PatternHelper.getIntFromString(getElement(priceOriginalLocator).getText());
        return (originPrice == thisOriginPrice && discount == thisDicount && (int)thisOriginPrice * (100 - discount) / 100 == (int)thisFinalPrice);
    }

    public InstallSteamPage goToInstallSteamPage(){
        new Button(installSteamLocator).confirm();
        return new InstallSteamPage();
    }
}
