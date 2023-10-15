package test.testng;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderTest {

    @Test(dataProvider = "usernameData")
    public void testDataProvider(String username){
        System.out.println(username);

    }

    @DataProvider
    public String[] usernameData(){
        return new String[]{"teo", "ty", "tun"};
    }
}
