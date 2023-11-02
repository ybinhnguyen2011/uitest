package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import url.Urls;

import java.time.Duration;
import java.util.List;

public class JsAlerst implements Urls {

    private final static By jsAlertSel = By.cssSelector("[conclick=\"jsAlert()\"]");
    private final static By jsAlertConfirmSel = By.cssSelector("[conclick=\"jsConfirm()\"]");
    private final static By jsAlertPromptSel = By.cssSelector("[conclick=\"jsPrompt()\"]");
    private final static By resultSel = By.cssSelector("#result");

    public static void main(String[] args) {

        // get a chrome session
        WebDriver driver = DriverFactory.getchrmomeDriver();




        try {
            // Navigate to the target page
            driver.get(baseUrlkuapp.concat(jsAlerlug));
            WebElement triggerJsAlertBtnElem;
            Alert alert;
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

            // JS_ALERT | OK
            triggerJsAlertBtnElem = driver.findElement(jsAlertSel);
            triggerJsAlertBtnElem.click();
            alert = wait.until(ExpectedConditions.alertIsPresent());
            System.out.println("Alert content: " + alert.getText());
            alert.accept();
            System.out.println("Result:" + driver.findElement(resultSel).getText());

            // JS_CONFIRM | CANCEL
            triggerJsAlertBtnElem = driver.findElement(jsAlertConfirmSel);
            triggerJsAlertBtnElem.click();
            alert = wait.until(ExpectedConditions.alertIsPresent());
            System.out.println("Alert content: " + alert.getText());
            alert.dismiss();
            System.out.println("Result:" + driver.findElement(resultSel).getText());

            // JS_PROMT
            triggerJsAlertBtnElem = driver.findElement(jsAlertConfirmSel);
            triggerJsAlertBtnElem.click();
            alert = wait.until(ExpectedConditions.alertIsPresent());
            System.out.println("Alert content: " + alert.getText());
            alert.sendKeys("My name is teo !!");
            alert.dismiss();
            System.out.println("Result:" + driver.findElement(resultSel).getText());





        } catch (Exception e) {
            e.printStackTrace();
        }

        // Quit the brower session
        driver.quit();
    }

    public static void handleAlert(Alert alert, boolean isAcceptions) {
        System.out.println("Alert content: " + alert.getText());
        if (isAcceptions) alert.accept();
        else alert.dismiss();

    }
}
