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
import steam.forms.InstallSteamPage;

import java.io.File;


public class TestSteam extends BaseEntity {
    @Test
    @Parameters({"year", "month", "day"})
    public void testSteam(String year, String month, String day) throws InterruptedException, NotAllElementsHasBeenFoundException {
        HomePage homePage = new HomePage();
        homePage.chooseLanguage();

        ActionsPage actionsPage = homePage.selectGamesComboBoxElement(PropsHelper.getLocProperty("actions"));
        actionsPage.changeTab(PropsHelper.getLocProperty("specials"));
        double[] priceAndDiscount = actionsPage.getTheBestDiscount();
        GamePage gamePage = actionsPage.confirmAgeIfNeeded(year, month, day);

        Assert.assertTrue(gamePage.checkPriceAndDiscount(priceAndDiscount));

        InstallSteamPage installSteamPage = gamePage.goToInstallSteamPage();
        System.out.println(installSteamPage.getNextSteamName());
        String steamName = installSteamPage.getNextSteamName();
        File steamFile = new File(PropsHelper.getProperty("rel_path_to_setup"));

        installSteamPage.DownloadSteam();
        installSteamPage.waitUntilFileDownloads(steamFile);
    }
}
