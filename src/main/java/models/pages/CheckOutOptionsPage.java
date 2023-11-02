package models.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckOutOptionsPage extends BasePage{

    private static final By checkoutAsGuestBtn = By.cssSelector(".checkout-as-guest-button");

    public CheckOutOptionsPage(WebDriver driver) {
        super(driver);
    }

    public void checkoutAsGuest(){
        component.findElement(checkoutAsGuestBtn).click();
    }

}
