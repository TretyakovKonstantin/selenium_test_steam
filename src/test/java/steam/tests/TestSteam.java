package steam.tests;

import framework.BaseEntity;
import framework.PropsHelper;
import framework.exceptions.NotAllElementsHasBeenFoundException;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import steam.forms.ActionsPage;
import steam.forms.GamePage;
import steam.forms.HomePage;


public class TestSteam extends BaseEntity {
    @Test
    @Parameters({"year", "month", "day"})
    public void testSteam(String year, String month, String day) throws InterruptedException, NotAllElementsHasBeenFoundException {
        HomePage homePage = new HomePage(getDriver());
        homePage.chooseLanguage();

        ActionsPage actionsPage = homePage.selectGamesComboBoxElement(PropsHelper.getLocProperty("actions"));
        actionsPage.changeTab(PropsHelper.getLocProperty("specials"));
        int[] priceAndDiscount = actionsPage.getTheBestDiscount();
        GamePage gamePage = actionsPage.confirmAgeIfNeeded(year, month, day);

        Assert.assertTrue(gamePage.checkPriceAndDiscount(priceAndDiscount));
        Thread.sleep(10000);
    }
}
