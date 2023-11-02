package models.pages;

import models.components.checkout.*;
import org.openqa.selenium.WebDriver;

public class CheckOutPage extends BasePage{

    public CheckOutPage(WebDriver driver) {
        super(driver);
    }

    public BillingAddressComponent billingAddressComps() {
        return findComponent(BillingAddressComponent.class, driver);
    }

    public ShippingAddressComponent shippingAddressComps() {
        return findComponent(ShippingAddressComponent.class, driver);
    }

    public ShippingMethodComponent shippingMethodComps() {
        return findComponent(ShippingMethodComponent.class, driver);
    }

    public PaymentMethodComponent paymentMethodComps() {
        return findComponent(PaymentMethodComponent.class, driver);
    }

    public PaymentInfoComponent paymentInfoComps() {
        return findComponent(PaymentInfoComponent.class, driver);
    }

    public ConfirmOrderComponent confirmOrderComps() {
        return findComponent(ConfirmOrderComponent.class, driver);
    }
}
