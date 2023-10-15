package test.testng;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test_data.computer.ComputerData;
import test_data.testdata.DataObjectBuilder;

import java.util.Arrays;

public class DataProviderComputerData {

    @Test(dataProvider = "computerData")
    public void testDataProvider(ComputerData computerData){
        System.out.println(computerData);

    }

    @DataProvider
    public ComputerData[] computerData(){
        String fileLocation = "/src/test/java/test_data/computer/CheapComputerDataList.json";
        ComputerData[] computerData =
                DataObjectBuilder.buildDataObjectFrom(fileLocation, ComputerData[].class);
        return computerData;

    }
}
