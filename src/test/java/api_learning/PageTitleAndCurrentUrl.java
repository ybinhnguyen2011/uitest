package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageTitleAndCurrentUrl {
    public static void main(String[] args) {
        // get a chrome session
        WebDriver driver = DriverFactory.getchrmomeDriver();

        try {
            // Navigate to the target page
            driver.get("https://beta.aloline.vn/auth");
            System.out.println(driver.getTitle());
            System.out.println(driver.getCurrentUrl());

            // Defind selector values
//            By usernameSel = By.cssSelector("[class='ng-pristine ng-invalid ng-touched']");
//            By passworSel = By.cssSelector("[class='ng-pristine ng-invalid ng-touched']");
//            By loginSel = By.cssSelector("[class='ng-pristine ng-invalid ng-touched']");


            // Find elements
//            WebElement usernameElem = driver.findElement(usernameSel);
//        WebElement passwordElem = driver.findElement(passworSel);
//        WebElement loginElem = driver.findElement(loginSel);

            // Interaction
//            usernameElem.sendKeys("0336400645");
//        passwordElem.sendKeys("1111");
//        loginElem.click();

            // DEbug purpose only

            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Quit the brower session
        driver.quit();
    }
}
