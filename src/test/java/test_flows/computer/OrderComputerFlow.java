package test_flows.computer;

import models.components.cart.CartItemRowComponent;
import models.components.cart.TotalComponent;
import models.components.checkout.BillingAddressComponent;
import models.components.checkout.PaymentInfoComponent;
import models.components.checkout.PaymentMethodComponent;
import models.components.checkout.ShippingMethodComponent;
import models.components.order.ComputeEssentialComponent;
import models.pages.CheckOutOptionsPage;
import models.pages.CheckOutPage;
import models.pages.ComputerItemDetailsPage;
import models.pages.ShoppingCartPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import test_data.computer.ComputerData;
import test_data.testdata.DataObjectBuilder;
import test_data.user.CreditCardType;
import test_data.user.PaymentMethod;
import test_data.user.UserDateObject;

import java.security.SecureRandom;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderComputerFlow<T extends ComputeEssentialComponent> {

    private final WebDriver driver;
    private final Class<T> computerEssentialComponent;
    private final ComputerData computerData;
    private final int quantity;
    private double totalItemPrice;
    private UserDateObject defaultCheckoutUser;
    private PaymentMethod paymentMethod;


    public OrderComputerFlow(WebDriver driver, Class<T> computerEssentialComponent, ComputerData computerData) {
        this.driver = driver;
        this.computerEssentialComponent = computerEssentialComponent;
        this.computerData = computerData;
        this.quantity = 1;
    }
    public OrderComputerFlow(WebDriver driver, Class<T> computerEssentialComponent, ComputerData computerData, int quantity) {
        this.driver = driver;
        this.computerEssentialComponent = computerEssentialComponent;
        this.computerData = computerData;
        this.quantity = quantity;
    }

    public  void buildCompSpecAndAddtoCart(){
        ComputerItemDetailsPage computerItemDetailsPage = new ComputerItemDetailsPage(driver);
        T computerEssentialComp = computerItemDetailsPage.computerComp(computerEssentialComponent);

        // Unselect all default options
        computerEssentialComp.unselectDefaultOptions();

        String processorFullStr = computerEssentialComp.selectProcessorType(computerData.getProcessorType());
        double processorAdditionalPrice = extractAdditionalPrice(processorFullStr);
        System.out.println("processorAdditionalPrice: " + processorAdditionalPrice);

        String ramFullStr = computerEssentialComp.selectRAMType(computerData.getRam());
        double ramAdditionalPrice = extractAdditionalPrice(ramFullStr);
        System.out.println("ramAdditionalPrice: " + ramAdditionalPrice);

        String hddFullStr = computerEssentialComp.selectHDD(computerData.getHdd());
        double additionalHddPrice = extractAdditionalPrice(hddFullStr);
        System.out.println("additionalHddPrice: " + additionalHddPrice);

        double additionalOsPrice = 0;
        if (computerData.getOs() != null) {
            String fullOsStr = computerEssentialComp.selectOS(computerData.getOs());
            additionalOsPrice = extractAdditionalPrice(fullOsStr);
        }
        System.out.println("additionalOsPrice: " + additionalOsPrice );

        String softwareFullStr = computerEssentialComp.selectSoftware(computerData.getSoftware());
        double additionalSoftwarePrice = extractAdditionalPrice(softwareFullStr);

        // Calculate item's price and add it cart
        double basePrice = computerEssentialComp.productPrice();
        double allAdditionalPrice = processorAdditionalPrice + ramAdditionalPrice + additionalHddPrice + additionalOsPrice
                + additionalSoftwarePrice;
        totalItemPrice = (basePrice + allAdditionalPrice) * quantity;

        // And  add to Cart
        computerEssentialComp.clickOnAddToCartBtn();
        computerEssentialComp.waitUntilItemAddedToCart();


        System.out.println(processorAdditionalPrice);
        System.out.println(ramAdditionalPrice);
        System.out.println(additionalHddPrice);
        System.out.println(additionalOsPrice);
        System.out.println(additionalSoftwarePrice);
        System.out.println(basePrice);
        System.out.println("totalItemPrice" + totalItemPrice);

        // Then Navigate to shopping cart
        computerItemDetailsPage.headerComp().clickOnShoppingCartLink();



        try {
            Thread.sleep(3000);

        }catch (Exception e){}
    }

    private double extractAdditionalPrice(String itemStr){
        double price = 0;
        Pattern pattern = Pattern.compile("\\[(.*?)\\]");
        Matcher matcher = pattern.matcher(itemStr);
        if (matcher.find()) {
            price = Double.parseDouble(matcher.group(1).replaceAll("[-+]", ""));
        }
        return price;
    }

    public void verifyShoppingCartPage(){
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        List<CartItemRowComponent> cartItemsRowCops = shoppingCartPage.cartItemRowComponents();
        if (cartItemsRowCops.isEmpty()){
            Assert.fail("[ERROR] There is no item displayed in the shopping cart");
        }

        double currentSubtotal = 0;
        double currentTotalUnitPrices = 0;
        for (CartItemRowComponent cartItemRowComp : cartItemsRowCops){
            currentSubtotal = currentSubtotal + cartItemRowComp.subTotal();
            currentTotalUnitPrices = currentTotalUnitPrices + cartItemRowComp.unitPrice() * cartItemRowComp.quantity();

        }

        Assert.assertEquals(currentSubtotal, currentTotalUnitPrices, "[ERROR] Shopping cart's sub total is incorrect" );
        TotalComponent totalComp = shoppingCartPage.totalComp();
        Map<String, Double> priceCategories = totalComp.priceCategories();
        double checkoutSubTotal = 0;
        double checkoutTotal = 0;
        double checkoutOtherFeesTotal = 0;

        for (String priceType : priceCategories.keySet()) {
            double priceValue = priceCategories.get( priceType);
            if (priceType.startsWith("Sub-Total")) {
                checkoutSubTotal = priceValue;
            } else if (priceType.startsWith("Total")) {
                checkoutTotal = priceValue;
            } else {
                checkoutOtherFeesTotal = checkoutOtherFeesTotal + priceValue;
            }
        }
        Assert.assertEquals(checkoutSubTotal, currentSubtotal, "[ERROR]... ");
        Assert.assertEquals(checkoutTotal, (checkoutSubTotal + checkoutOtherFeesTotal) , "[ERROR]... ");

    }

    public void agreeTOSAndCheckout(){

        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        shoppingCartPage.totalComp().agreeTOS();
        shoppingCartPage.totalComp().clickOnCheckOutBtn();
        new CheckOutOptionsPage(driver).checkoutAsGuest();

        try {
            Thread.sleep(3000);

        }catch (Exception e){}
    }

    public void inputBillingAddress(){
        String defaultCheckoutUserJSONLoc ="/src/test/java/test_data/computer/DefaultCheckoutUser.json";
        defaultCheckoutUser = DataObjectBuilder.buildDataObjectFrom(defaultCheckoutUserJSONLoc, UserDateObject.class);
        CheckOutPage checkOutPage = new CheckOutPage(driver);
        BillingAddressComponent billingAddressComponent = checkOutPage.billingAddressComps();
        billingAddressComponent.selectInputNewAddress();
        billingAddressComponent.inputFirstname(defaultCheckoutUser.getFirstName());
        billingAddressComponent.inputLastname(defaultCheckoutUser.getLastName());
        billingAddressComponent.inputEmail(defaultCheckoutUser.getEmail());
        billingAddressComponent.selectCountry(defaultCheckoutUser.getCountry());
        billingAddressComponent.selectState(defaultCheckoutUser.getState());
        billingAddressComponent.inputAdd1(defaultCheckoutUser.getAdd1());
        billingAddressComponent.inputZIPcode(defaultCheckoutUser.getZipCode());
        billingAddressComponent.inputPhoneNo(defaultCheckoutUser.getPhoneNum());
        billingAddressComponent.clickOnContinueBtn();


    }

    public void inputShippingAddress(){
        CheckOutPage checkOutPage = new CheckOutPage(driver);
        checkOutPage.shippingAddressComps().clickOnContinueBtn();
    }

    public void selectShippingMethod(){

        List<String> shippingMethods = Arrays.asList("Ground", "Next Day Air", "2nd Day Air");
        String randomShippingMethod = shippingMethods.get(new SecureRandom().nextInt(shippingMethods.size()));
        CheckOutPage checkOutPage = new CheckOutPage(driver);
        ShippingMethodComponent shippingMethodComps = checkOutPage.shippingMethodComps();
        shippingMethodComps.selectShippingMethod(randomShippingMethod).clickOnContinueButton();
        try {
            Thread.sleep(3000);
        }catch (Exception ignored){}
    }

    public void selectPaymentMethod(){
        this.paymentMethod = PaymentMethod.COD;
    }

    public void selectPaymentMethod(PaymentMethod paymentMethod){
        if (paymentMethod == null){
            throw new IllegalArgumentException("[ERROR] Payment method can't be null");

        }
        this.paymentMethod = paymentMethod;

        CheckOutPage checkOutPage = new CheckOutPage(driver);
        PaymentMethodComponent paymentMethodComp = checkOutPage.paymentMethodComps();
        switch (paymentMethod){
            case CHECK_MONEY_ORDER:
                paymentMethodComp.selectCheckMoneyOrderMethod();
                break;
            case CREDIT_CARD:
                paymentMethodComp.selectCreditCardMethod();
                break;
            case PURCHASE_ORDER:
                paymentMethodComp.selectPurchaseOrderMethod();
                break;
            default:
                paymentMethodComp.selectCODMethod();
        }

    }
    public void inputPaymentInfo(CreditCardType creditCardType){
        this.creditCardType = creditCardType;
        CheckOutPage checkOutPage = new CheckOutPage(driver);
        PaymentInfoComponent paymentInfoComponent = checkOutPage.paymentInfoComps();

        if (this.paymentMethod.equals(PaymentMethod.PURCHASE_ORDER)){
            // This can be dynamic as well
            paymentInfoComponent.inputPurchaseNum("123");

        } else if(this.paymentMethod.equals(PaymentMethod.CREDIT_CARD)) {
            paymentInfoComponent.selectCardType(creditCardType);
            String cardHolderFirstName = this.defaultCheckoutUser.getFirstName();
            String cardHolderLastName = this.defaultCheckoutUser.getLastName();
            paymentInfoComponent.inputCardHolderName(cardHolderFirstName + " " + cardHolderLastName);
            String cardNumber = creditCardType.equals(CreditCardType.VISA) ? "4012888888881881" : "6011000990139424";
            paymentInfoComponent.inputCardCode(cardNumber);

            // Select current month and next year
            Calendar calendar = new GregorianCalendar();
            paymentInfoComponent.inputExpireMonth(String.valueOf(calendar.get(Calendar.MONTH) +1));
            paymentInfoComponent.inputExpireYear(String.valueOf(calendar.get(Calendar.YEAR) +1));
            paymentInfoComponent.inputCardCode("123");
            paymentInfoComponent.clickOnContinueBtn();

        } else if(this.paymentMethod.equals(PaymentMethod.COD)) {
            //TODO: add verification
        } else {
            //TODO: verify
        }



    }

    public void confirmOrder(){
        //TODO: add verification method
        new CheckOutPage(driver).confirmOrderComps().clickOnContinueBtn();

    }


}
