package test_flows.global;

import models.components.global.footer.FooterColumnComponent;
import org.openqa.selenium.WebDriver;

public class FooterTestFlow {

    private final WebDriver driver;



    public FooterTestFlow(WebDriver driver) {
        this.driver = driver;
    }

    public void verifyFooterComponent(){
        verifyInformationClomn();
        verifyCustomerSevice();
        verifyAccountClomn();
        verifyFollowUsClomn();
    }

    private void verifyInformationClomn() {
    }

    private void verifyCustomerSevice() {
    }

    private void verifyAccountClomn() {
    }

    private void verifyFollowUsClomn() {
    }

    private static void testFooterColumn(FooterColumnComponent footerColumnComponent) {
        System.out.println(footerColumnComponent.headerElem().getText());
        footerColumnComponent.linksElem().forEach(link -> {
            System.out.println(link.getText());
            System.out.println(link.getAttribute("href"));
        });

    }
}
