package pom_tests;

import driver.DriverFactory;
import models.pages.LoginPageMod01;
import org.openqa.selenium.WebDriver;

import static url.Urls.*;

public class LoginTestMod01 {
    public static void main(String[] args) {
        // get a chrome session
        WebDriver driver = DriverFactory.getchrmomeDriver();

        try {
            // Navigate to the target page
            driver.get(baseUrlkuapp.concat(loginlughero));

            LoginPageMod01 loginPage = new LoginPageMod01(driver);
            loginPage.username().sendKeys("taokydau@gmail.com");
            loginPage.passwork().sendKeys("0000");
            loginPage.loginBtn().click();


        } catch (Exception e) {
            e.printStackTrace();
        }

        // Quit the brower session
        driver.quit();
    }

}
