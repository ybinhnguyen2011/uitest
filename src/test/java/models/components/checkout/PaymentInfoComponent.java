package models.components.checkout;

import models.components.Component;
import models.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import test_data.user.CreditCardType;

@ComponentCssSelector("#opc-payment_info")
public class PaymentInfoComponent extends Component {

    public static final By creditCardDropdownSel = By.cssSelector("#CreditCardType");
    public static final By cardHolderNameSel = By.cssSelector("#CardHolderName");
    public static final By cardNumberSel = By.cssSelector("#CardNumber");
    public static final By cardExpireMonthDropdownSel = By.cssSelector("#ExpireMonth");
    public static final By cardExpireYearDropdownSel = By.cssSelector("#ExpireYear");
    public static final By cardCodeSel = By.cssSelector("#CardCode");
    public static final By purchaseNumSel = By.cssSelector("#PurchaseOrderNumSel");
    public static final By continueBtnSel = By.cssSelector(".payment-info-next-step-button");

    public PaymentInfoComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public void selectCardType(CreditCardType creditCardType){

        if (creditCardType == null){
            throw new IllegalArgumentException("[ERROR] Credit card type can't be null");
        }
        Select select = new Select(component.findElement(creditCardDropdownSel));
        switch (creditCardType){
            case VISA:
                select.selectByVisibleText("Visa");
                break;
            case AMEX:
                select.selectByVisibleText("Amex");
                break;
            case MASTER_CARD:
                select.selectByVisibleText("Master card");
                break;
            case DISCOVER:
                select.selectByVisibleText("Discover");

        }

    }

    public void inputCardHolderName(String name) {
        component.findElement(cardHolderNameSel).sendKeys(name);
    }

    public void inputCardNumber(String number) {
        component.findElement(cardNumberSel).sendKeys(number);
    }

    public void inputExpireMonth(String month) {
        Select select = new Select(component.findElement(cardExpireMonthDropdownSel));
        select.selectByValue(month);
    }

    public void inputExpireYear(String year) {
        Select select = new Select(component.findElement(cardExpireYearDropdownSel));
        select.selectByVisibleText(year);
    }

    public void inputCardCode(String code) {
        component.findElement(cardCodeSel).sendKeys(code);
    }

    public void inputPurchaseNum(String number){
        component.findElement(purchaseNumSel).sendKeys(number);
    }

    public void clickOnContinueBtn(){
        WebElement continueBtn = component.findElement(continueBtnSel);
        continueBtn.click();
        wait.until(ExpectedConditions.invisibilityOf(continueBtn));
    }
}
