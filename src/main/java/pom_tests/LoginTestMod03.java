package pom_tests;

import driver.DriverFactory;
import models.pages.LoginPageMod02;
import models.pages.LoginPageMod03;
import org.openqa.selenium.WebDriver;

import static url.Urls.baseUrlkuapp;
import static url.Urls.loginlughero;

public class LoginTestMod03 {
    public static void main(String[] args) {
        // get a chrome session
        WebDriver driver = DriverFactory.getchrmomeDriver();

        try {
            // Navigate to the target page
            driver.get(baseUrlkuapp.concat(loginlughero));

            LoginPageMod03 loginPage = new LoginPageMod03(driver);
            loginPage.loginFormComponent().inputUsername("taokydau@gmail.com");
            loginPage.loginFormComponent().inputPasswork("0000");
            loginPage.loginFormComponent().clickLoginBtn();


        } catch (Exception e) {
            e.printStackTrace();
        }

        // Quit the brower session
        driver.quit();
    }

}
