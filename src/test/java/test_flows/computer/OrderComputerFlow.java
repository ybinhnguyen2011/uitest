package test_flows.computer;

import models.components.order.ComputeEssentialComponent;
import models.pages.ComputerItemDetailsPage;
import org.openqa.selenium.WebDriver;

public class OrderComputerFlow<T extends ComputeEssentialComponent> {

    private final WebDriver driver;
    private final Class<T> computerEssentialComponent;

    public OrderComputerFlow(WebDriver driver, Class<T> computerEssentialComponent) {
        this.driver = driver;
        this.computerEssentialComponent = computerEssentialComponent;
    }

    public  void buildCompSpecAndAddtoCart(){
        ComputerItemDetailsPage computerItemDetailsPage = new ComputerItemDetailsPage(driver);
        T computerEssentialComp = computerItemDetailsPage.computerComp(computerEssentialComponent);
        computerEssentialComp.selectProcessorType("Fast");
        computerEssentialComp.selectRAMType("4 GB");

        try {
            Thread.sleep(5000);

        }catch (Exception e){}
    }
}
