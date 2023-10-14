package models.pages;

import models.components.order.ComputeEssentialComponent;
import org.openqa.selenium.WebDriver;

public class ComputerItemDetailsPage extends BasePage{

    public ComputerItemDetailsPage(WebDriver driver) {
        super(driver);
    }

    public<T extends ComputeEssentialComponent> T computerComp(Class<T> computerEssentialCompClass) {
        return findComponent(computerEssentialCompClass, driver);
    }
}
