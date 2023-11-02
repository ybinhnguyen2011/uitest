package support.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class WaitForEmlentEnabled implements ExpectedCondition<Boolean> {
    private By slector;

    public WaitForEmlentEnabled(By slector) {
        this.slector = slector;
    }

    @Override
    public Boolean apply(WebDriver driver){
        return driver.findElement(slector).isEnabled();
    }

    @Override
    public String toString() {
        return "Element located by" + this.slector.toString();
    }
}
