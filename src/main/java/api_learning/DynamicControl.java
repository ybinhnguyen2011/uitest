package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import support.ui.WaitForEmlentEnabled;
import support.ui.WaitMoreThanOneTab;

import java.time.Duration;

import static url.Urls.*;

public class DynamicControl {
    public static void main(String[] args) {

        // get a chrome session
        WebDriver driver = DriverFactory.getchrmomeDriver();

        try {
            // Navigate to the target page
            driver.get(baseUrlkuapp.concat(dynamicControllug));

            // Define parent locator | 2 form | checkbox form + input form
            By checkboxSel = By.id("checkbox-example");
            By checkboxcheckSel = By.tagName("input");

            By inputFormSel = By.id("checkbox-example");

            // Checkbox form interaction
            WebElement checkboxFormElem = driver.findElement(checkboxSel);
            WebElement checkboxElem = checkboxFormElem.findElement(checkboxcheckSel);
            if (!checkboxElem.isSelected()) checkboxElem.click();

            // Debug purpose Only
            Thread.sleep(2000);

            // input form interaction
            WebElement inputFormElem = driver.findElement(inputFormSel);
            WebElement inputElem = checkboxFormElem.findElement(checkboxcheckSel); // Dùng lại checkboxcheckSel
            WebElement inputBtnElem = checkboxFormElem.findElement(By.tagName("button"));
            if (!inputElem.isSelected()) inputBtnElem.click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loading")));
            wait.until(new WaitForEmlentEnabled(checkboxcheckSel));
            inputElem.sendKeys("Tui là Teèo hello các baạn");


            // Debug purpose Only
            Thread.sleep(2000);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Quit the brower session
        driver.quit();
    }
}
