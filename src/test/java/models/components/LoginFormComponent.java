package models.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@ComponentCssSelector(value = "#login")
public class LoginFormComponent {

    private final WebDriver driver;
    private final static By usernameSel = By.id("username");
    private final static By passworkSel = By.cssSelector("#password");
    private final static By loginBtnSel = By.cssSelector("[type='submit']");

    public LoginFormComponent(WebDriver driver) {
        this.driver = driver;
    }

    public void inputUsername(String usernameTxt) {
        driver.findElement(usernameSel).sendKeys(usernameTxt);
    }
    public void inputPasswork(String passworkTxt) {
        driver.findElement(passworkSel).sendKeys(passworkTxt);
    }
    public void clickLoginBtn() {
        driver.findElement(loginBtnSel).click();
    }
}
