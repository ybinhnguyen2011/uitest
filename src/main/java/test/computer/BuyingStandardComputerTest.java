package test.computer;

import models.components.order.StandardComputerComponent;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.test.BaseTest;
import test_data.computer.ComputerData;
import test_data.testdata.DataObjectBuilder;
import test_data.user.CreditCardType;
import test_data.user.PaymentMethod;
import test_flows.computer.OrderComputerFlow;
import url.Urls;

public class BuyingStandardComputerTest extends BaseTest implements Urls {

    @Test(dataProvider = "computerData")
    public void testStandardComputerBuying(ComputerData computerData){


        WebDriver driver = getDriver();
        getDriver().get(demoBaseUrl.concat("/build-your-own-computer"));

        OrderComputerFlow<StandardComputerComponent> orderComputerFlow =
                new OrderComputerFlow<>(driver, StandardComputerComponent.class, computerData);
        orderComputerFlow.buildCompSpecAndAddtoCart();
        orderComputerFlow.verifyShoppingCartPage();
        orderComputerFlow.agreeTOSAndCheckout();
        orderComputerFlow.inputBillingAddress();
        orderComputerFlow.inputShippingAddress();
        orderComputerFlow.selectShippingMethod();
        orderComputerFlow.selectPaymentMethod(PaymentMethod.CREDIT_CARD);
        orderComputerFlow.inputPaymentInfo(CreditCardType.VISA);
        orderComputerFlow.confirmOrder();
    }

    @DataProvider
    public ComputerData[] computerData(){
        String fileLocation = "/src/main/java/test_data/computer/StandardComputerDataList.json";
        ComputerData[] computerData =
                DataObjectBuilder.buildDataObjectFrom(fileLocation, ComputerData[].class);
        return computerData;

    }
}
