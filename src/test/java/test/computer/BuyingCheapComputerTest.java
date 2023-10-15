package test.computer;

import models.components.order.CheapComputerComponent;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.test.BaseTest;
import test_data.computer.ComputerData;
import test_data.testdata.DataObjectBuilder;
import test_flows.computer.OrderComputerFlow;
import url.Urls;

public class BuyingCheapComputerTest extends BaseTest implements Urls {

    @Test(dataProvider = "computerData")
    public void testCheapComputerBuying(ComputerData computerData){

        driver.get(demoBaseUrl.concat("/build-your-cheap-own-computer"));

        OrderComputerFlow<CheapComputerComponent> orderComputerFlow =
                new OrderComputerFlow<>(driver, CheapComputerComponent.class, computerData);
        orderComputerFlow.buildCompSpecAndAddtoCart();
    }

    @DataProvider
    public ComputerData[] computerData(){
        String fileLocation = "/src/test/java/test_data/computer/CheapComputerDataList.json";
        ComputerData[] computerData =
                DataObjectBuilder.buildDataObjectFrom(fileLocation, ComputerData[].class);
        return computerData;

    }
}
