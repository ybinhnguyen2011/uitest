package test.global.footer;

import driver.DriverFactory;
import models.components.global.footer.CustomerServiceColumnComponent;
import models.components.global.footer.FooterColumnComponent;
import models.components.global.footer.InformationColumnComponent;
import models.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import support.verification.Verifier;
import test_flows.global.FooterTestFlow;
import url.Urls;

public class FooterTest {

    @Test(priority = 1)
    public void testFooterHomePage() {
        WebDriver driver = DriverFactory.getchrmomeDriver();
        driver.get(Urls.demoBaseUrl);
        try {
            HomePage homePage = new HomePage(driver);
            InformationColumnComponent informationColumnComponent = homePage.footerComponent().informationColumnComponent();
            CustomerServiceColumnComponent customerServiceColumnComponent = homePage.footerComponent().customerServiceColumnComponent();
            testFooterColumn(informationColumnComponent);
            testFooterColumn(customerServiceColumnComponent);
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }
        driver.quit();

    }

    @Test(priority = 2, dependsOnMethods = {"testFooterRegisterPage"})
    public void testFooterCategoryPage() {
        WebDriver driver = DriverFactory.getchrmomeDriver();
        driver.get(Urls.demoBaseUrl);
        FooterTestFlow footerTestFlow = new FooterTestFlow(driver);
        footerTestFlow.verifyFooterComponent();
    }

    @Test(priority = 3)
    public void testFooterRegisterPage() {
        String expected = "testFooterRegisterPage";
        String actual = "lol";
//        Verifier.verifyEquals(expected, actual); // tự handle

        // Hard assertion => sai nó se ngưng chạy các dòng dưới
//        Assert.assertEquals(expected, actual); // của test NG
        Assert.assertTrue(expected.equals(actual)); // của test NG
        Assert.assertFalse(expected.equals(actual); // của test NG
        Assert.fail(); // của test NG
    }

    @Test(priority = 4)
    public void testFooterLoginPage() {
        // có lỗi sẽ cũng thuc thi =  ngoại trừ assertALL => nó sẽ tính tổng những thằng treen nó bị lỗi
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(1,2);
        softAssert.assertEquals(true,true);
        softAssert.assertEquals(1,4);

        softAssert.assertAll();

        System.out.println("Hello");
    }


    private static void testFooterColumn(FooterColumnComponent footerColumnComponent) {
        System.out.println(footerColumnComponent.headerElem().getText());
        footerColumnComponent.linksElem().forEach(link -> {
            System.out.println(link.getText());
            System.out.println(link.getAttribute("href"));
        });

    }

}
