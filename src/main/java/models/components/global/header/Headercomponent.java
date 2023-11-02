package models.components.global.header;

import models.components.Component;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Headercomponent extends Component {

    private final static By shoppingCartLinkSel = By.cssSelector("#topcartlink");


    public Headercomponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public void clickOnShoppingCartLink(){
        WebElement shoppingCartLinkElem = component.findElement(shoppingCartLinkSel);
        scrollUpToElement(shoppingCartLinkElem);
        shoppingCartLinkElem.click();

    }
}
