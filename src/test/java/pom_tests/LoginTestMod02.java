package pom_tests;

import driver.DriverFactory;
import models.pages.LoginPageMod01;
import models.pages.LoginPageMod02;
import org.openqa.selenium.WebDriver;

import static url.Urls.baseUrlkuapp;
import static url.Urls.loginlughero;

public class LoginTestMod02 {
    public static void main(String[] args) {
        // get a chrome session
        WebDriver driver = DriverFactory.getchrmomeDriver();

        try {
            // Navigate to the target page
            driver.get(baseUrlkuapp.concat(loginlughero));

            LoginPageMod02 loginPage = new LoginPageMod02(driver);
            loginPage.inputUsername("taokydau@gmail.com");
            loginPage.inputPasswork("0000");
            loginPage.clickLoginBtn();


        } catch (Exception e) {
            e.printStackTrace();
        }

        // Quit the brower session
        driver.quit();
    }

}
