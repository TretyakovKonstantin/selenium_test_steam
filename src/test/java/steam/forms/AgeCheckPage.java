package steam.forms;

import framework.BaseForm;
import framework.PropsHelper;
import framework.elements.Button;
import framework.elements.ComboBox;
import org.openqa.selenium.By;

public class AgeCheckPage extends BaseForm {

    private By yearComboBoxLocator = By.name("ageYear");
    private By monthComboBoxLocator = By.name("ageMonth");
    private By dayComboBoxLocator = By.name("ageDay");

    private String yearLocator = "//select[@name=\"ageYear\"]/option[@value=\"%d\"]";
    private String monthLocator = "//select[@name=\"ageMonth\"]/option[@value=\"%s\"]";
    private String dayLocator = "//select[@name=\"ageDay\"]/option[@value=\"%d\"]";
    private String confirmLocator = "//span[contains(., \"%s\")]/..";

    public void confirmAge(int day, String month, int year) {
        ComboBox yearComboBox = new ComboBox(yearComboBoxLocator);
        yearComboBox.clickAndSelectItem(By.xpath(String.format(yearLocator, year)));

        ComboBox monthComboBox = new ComboBox(monthComboBoxLocator);
        monthComboBox.clickAndSelectItem(By.xpath(String.format(monthLocator, month)));

        ComboBox dayComboBox = new ComboBox(dayComboBoxLocator);
        dayComboBox.clickAndSelectItem(By.xpath(String.format(dayLocator, day)));

        Button btnConfirm = new Button(By.xpath(String.format(confirmLocator, PropsHelper.getLocProperty("enter"))));
        btnConfirm.confirm();
    }
}
