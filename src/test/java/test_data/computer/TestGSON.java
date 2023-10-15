package test_data.computer;

import test_data.testdata.DataObjectBuilder;

import java.util.Arrays;

public class TestGSON {

    // Convert from JSON to Java Object
    // Convert from Java Object to Gson
    public static void main(String[] args) {
        // explore Gson Features
        testDataBuilder();
    }

    private static void testDataBuilder() {
        String fileLocation = "/src/test/java/test_data/computer/CheapComputerDataList.json";
        ComputerData[] computerData =
                DataObjectBuilder.buildDataObjectFrom(fileLocation, ComputerData[].class);
        System.out.println(Arrays.toString(computerData));
    }

}
