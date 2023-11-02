package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FormInteractionMultipleMacthing {
    public static void main(String[] args) {
        // get a chrome session
        WebDriver driver = DriverFactory.getchrmomeDriver();

        try {
            // Navigate to the target page
            driver.get("https://beta.aloline.vn/auth");

            // Defind selector values
            By loginInputSel = By.tagName("input");
//            By passworSel = By.cssSelector("[class='ng-pristine ng-invalid ng-touched']");
//            By loginSel = By.cssSelector("[class='ng-pristine ng-invalid ng-touched']");


            // Find elements
//            driver.findElement(usernameSel).sendKeys("a0336400645");
//        WebElement passwordElem = driver.findElement(passworSel);
//        WebElement loginElem = driver.findElement(loginSel);

            // Interaction

            List <WebElement> loginFormFieldsElem = driver.findElements(loginInputSel);
            final int USERNAME_INDEX = 0;
            final int PASSWORD_INDEX = 1;
            if (loginFormFieldsElem.isEmpty()) {
            loginFormFieldsElem.get(USERNAME_INDEX).sendKeys("0909124124");
            loginFormFieldsElem.get(PASSWORD_INDEX).sendKeys("0000");
            }else {
//                Assert.fail("Loi roi =>>>>>>>>");
            }


            // DEbug purpose only

            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Quit the brower session
        driver.quit();
    }
}
