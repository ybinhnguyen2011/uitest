package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Dropdown {
    public static void main(String[] args) {
        // get a chrome session
        WebDriver driver = DriverFactory.getchrmomeDriver();

        try {
            // Navigate to the target page
            driver.get("https://beta.aloline.vn/auth");

            // Defind selector values
            By loginInputSel = By.tagName("input");
//            By passworSel = By.cssSelector("[class='ng-pristine ng-invalid ng-touched']");
            By loginSel = By.tagName("button");


            // Find elements
//            driver.findElement(usernameSel).sendKeys("a0336400645");
//        WebElement passwordElem = driver.findElement(passworSel);
//        WebElement loginElem = driver.findElement(loginSel);

            // Interaction

            List <WebElement> loginFormFieldsElem = driver.findElements(loginInputSel);
            final int USERNAME_INDEX = 0;
            final int PASSWORD_INDEX = 1;

            // Get attributes values
            System.out.println("Login btn type: " + driver.findElement(loginSel).getAttribute("type"));
            System.out.println("Login btn background color: " + driver.findElement(loginSel).getCssValue("background-color"));

            loginFormFieldsElem.get(USERNAME_INDEX).sendKeys("0909124124");
            loginFormFieldsElem.get(PASSWORD_INDEX).sendKeys("0000");
            driver.findElement(loginSel).click();

            // goback to previous page
            driver.navigate().back();

            // Refresh page
            driver.navigate().refresh();



            // DEbug purpose only

            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Quit the brower session
        driver.quit();
    }
}
