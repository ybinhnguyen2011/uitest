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
import test.test.BaseTest;
import test_flows.global.FooterTestFlow;
import url.Urls;

public class FooterTest extends BaseTest {



    @Test()
    public void testFooterCategoryPage() {

            driver.get(Urls.demoBaseUrl);
            FooterTestFlow footerTestFlow = new FooterTestFlow(driver);
            footerTestFlow.verifyProductCatFooterComponent();

    }

    @Test()
    public void testFooterRegisterPage() {}

    @Test()
    public void testFooterLoginPage() {

    }

    @Test()
    public void testFooterHomePage() {

    }




}
