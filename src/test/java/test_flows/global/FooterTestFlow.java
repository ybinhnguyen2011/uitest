package test_flows.global;

import models.components.global.footer.FooterColumnComponent;
import models.components.global.footer.FooterComponent;
import models.components.global.header.TopMenuComponent;
import models.pages.BasePage;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import url.Urls;

import java.security.SecureRandom;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FooterTestFlow {

    private final WebDriver driver;



    public FooterTestFlow(WebDriver driver) {
        this.driver = driver;
    }

    public void verifyFooterComponent(){
        BasePage basePage = new BasePage(driver);
        FooterComponent footerComponent = basePage.footerComponent();
        verifyInformationClomn(footerComponent.informationColumnComponent());
        verifyCustomerSevice(footerComponent.customerServiceColumnComponent());
//        verifyAccountClomn(footerComponent.accountColumnComponent());
//        verifyFollowUsClomn(footerComponent.followUsColumnComponent());
    }

    private void verifyInformationClomn(FooterColumnComponent footerColumnComp) {

        String baseUrl = Urls.demoBaseUrl;
        List<String> expectedLinkTexts = Arrays.asList("Sitemap",
                "Shipping & Returns",
                "Privacy Notice",
                "Conditions of Use",
                "About us",
                "Contact us");
        List<String> expectedHrefs = Arrays.asList(
                baseUrl + "/sitemap",
                baseUrl + "/shipping-returns",
                baseUrl + "/privacy-notice",
                baseUrl + "/conditions-of-Use",
                baseUrl + "/about-us",
                baseUrl + "/contactus");
        verifyFooterColumn(footerColumnComp, expectedLinkTexts, expectedHrefs);
    }

    private void verifyCustomerSevice(FooterColumnComponent footerColumnComp) {

        String baseUrl = Urls.demoBaseUrl;
        List<String> expectedLinkTexts = Arrays.asList(
                baseUrl + "Search",
                baseUrl + "News",
                baseUrl + "Blog",
                baseUrl + "Recently viewed products",
                baseUrl + "Compare products list",
                baseUrl + "New products");
        List<String> expectedHrefs = Arrays.asList("/search",
                "/news",
                "/blog",
                "/recentlyviewedproducts",
                "/compareproductslist",
                "/newproducts");
        verifyFooterColumn(footerColumnComp, expectedLinkTexts, expectedHrefs);
    }

    private void verifyAccountClomn(FooterColumnComponent footerColumnComp) {
        List<String> expectedLinkTexts = new ArrayList<>();
        List<String> expectedHrefs = new ArrayList<>();
        verifyFooterColumn(footerColumnComp, expectedLinkTexts, expectedHrefs);
    }

    private void verifyFollowUsClomn(FooterColumnComponent footerColumnComp) {
        List<String> expectedLinkTexts = new ArrayList<>();
        List<String> expectedHrefs = new ArrayList<>();
        verifyFooterColumn(footerColumnComp, expectedLinkTexts, expectedHrefs);
    }

    public void verifyProductCatFooterComponent(){
        //Random pickup an item
        BasePage basePage = new BasePage(driver);
        TopMenuComponent topMenuComp = basePage.topMenuComp();
        List<TopMenuComponent.MainCatItem> mainCatsElem = topMenuComp.mainItemsElem();
        if (mainCatsElem.isEmpty()) {
            Assert.fail("[ERROR] There are no items on top menu");
        }
        TopMenuComponent.MainCatItem randomMainItemElem = mainCatsElem.get(new SecureRandom().nextInt(mainCatsElem.size()));

        String randomCatHref = randomMainItemElem.catItemLinkElem().getAttribute("href");

        // Get sublist
        List<TopMenuComponent.CatItemComponent> catItemComps = randomMainItemElem.catItemComps();

        if (catItemComps.isEmpty()) {
            randomMainItemElem.catItemLinkElem().click();
        } else {
            int randomIndex = new SecureRandom().nextInt(catItemComps.size());
            TopMenuComponent.CatItemComponent randomCatItemComp = catItemComps.get(randomIndex);
            randomCatHref = randomCatItemComp.getComponent().getAttribute("href");
            randomCatItemComp.getComponent().click();
        }

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.urlContains(randomCatHref));

        } catch (TimeoutException e) {
            Assert.fail("[ERROR] Target page is not matched!");
        }



        // Verify footter Component
        verifyFooterComponent();
    }

    private static void verifyFooterColumn(FooterColumnComponent footerColumnComponent,
                                         List<String> expectedLinkTexts,
                                         List<String> expectedHrefs) {
        List<String> actualLinkTexts = new ArrayList<>();
        List<String> actualHrefs = new ArrayList<>();
        for (WebElement link: footerColumnComponent.linksElem()) {
            actualLinkTexts.add(link.getText().trim());
            actualHrefs.add(link.getAttribute("href"));
        }
        if (actualLinkTexts.isEmpty() || actualHrefs.isEmpty()) {
            Assert.fail("[ERR] Text or hyperlinks is empty in footer column!!");
        }
        // verify linktext
        Assert.assertEquals(actualLinkTexts, expectedLinkTexts, "[ERR] Actual and expected " +
                "Link text are different");

        // verify hyperlinks
        Assert.assertEquals(actualHrefs, expectedHrefs, "[ERR] Actual and expected " +
                "Hrefs are different");

    }
}
