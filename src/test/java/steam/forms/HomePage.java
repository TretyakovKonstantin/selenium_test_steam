package steam.forms;

import framework.BaseForm;
import framework.PropsHelper;
import framework.elements.ComboBox;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.util.logging.Level;
import java.util.logging.Logger;

public class HomePage extends BaseForm {

    private String gamesLocator = "//a[@class = 'popup_menu_item' and contains(., \"%s\")]";
    private String gamesListLocator = "//div[@id=\"genre_tab\"]/span[@class=\"pulldown\"]";
    private String languageCBElementLocator = "//a[@href=\"?l=%s\"]";

    private By languageComboBoxLocator = By.id("language_pulldown");

    public ComboBox getGamesComboBox() {
        By comboBoxLocator = By.xpath(gamesListLocator);
        return new ComboBox(comboBoxLocator);
    }

    public ActionsPage selectGamesComboBoxElement(String name) {
        System.out.println(name);
        getGamesComboBox().selectNotStaleItem(By.xpath(String.format(gamesLocator, name)));
        return new ActionsPage();
    }

    public void chooseLanguage() {
        ComboBox languageComboBox = new ComboBox(languageComboBoxLocator);
        if (languageComboBox.getText().equals(PropsHelper.getLocProperty("language")))
            return;
        String language = "";
        switch (PropsHelper.getProperty("language")) {
            case "en":
                language = "english";
                break;
            case "ru":
                language = "russian";
                break;
        }
        try {
            languageComboBox.clickAndSelectItem(By.xpath(String.format(languageCBElementLocator, language)));
        } catch (NoSuchElementException e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Выбранный язык не был обнаружен");
        }
    }

}