package steam.forms;

import framework.BaseForm;
import framework.PropsHelper;
import framework.elements.Button;
import org.openqa.selenium.By;

import java.io.File;
import java.util.Scanner;

public class InstallSteamPage extends BaseForm {

    private By downloadSteamLocator = By.xpath("//div[@class=\"about_install_ctn\"]//a");

    public void DownloadSteam() {
        Button btnDownloadSteam = new Button(downloadSteamLocator);
        btnDownloadSteam.confirm();
    }

    public String getNextSteamName() {
        String pathToFile = PropsHelper.getProperty("rel_path_to_setup");
        String steamSetupName = PropsHelper.getProperty("steam_setup");
        File steamSetupFile = new File(pathToFile + steamSetupName + ".exe");
        int index = 1;
        while (steamSetupFile.exists()) {
            String steamSetupNameWithIndex = steamSetupName + "(" + index + ").exe";
            steamSetupFile = new File(pathToFile + steamSetupNameWithIndex);
            if (!steamSetupFile.exists())
                return steamSetupNameWithIndex;
            index++;
        }
        return steamSetupName + ".exe";
    }


}
