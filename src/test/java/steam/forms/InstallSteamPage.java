package steam.forms;

import framework.BaseForm;
import framework.elements.Button;
import org.openqa.selenium.By;

public class InstallSteamPage extends BaseForm {

    private By downloadSteamLocator = By.xpath("//div[@class=\"about_install_ctn\"]//a");

    public void DownloadSteam(){
        Button btnDownloadSteam = new Button(downloadSteamLocator);
        btnDownloadSteam.confirm();
    }

}
