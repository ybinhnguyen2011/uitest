package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import support.ui.SelectEx;
import support.ui.WaitMoreThanOneTab;

import java.time.Duration;

import static url.Urls.*;

public class ExplicitWait {

    public static void main(String[] args) {

        // get a chrome session
        WebDriver driver = DriverFactory.getchrmomeDriver();

        try {
            // Navigate to the target page
            driver.get(baseUrlALOLINE.concat(loginlug));

            // Dropdown locator and element
            By nameSel = By.cssSelector("#teo");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            // ==> tìm không ra sẽ hiện lỗi timeout khi dùng
//            wait.until(ExpectedConditions.visibilityOfElementLocated(nameSel)); // tim .5/1 lan trong 10s = 20 lan tim
            // =>> chổ parameter sẽ hiện lỗi on such emlements
//            wait.until(ExpectedConditions.visibilityOf(driver.findElement(nameSel)));

            // Dùng class vừa tạo
            wait.until(new WaitMoreThanOneTab());


        } catch (Exception e) {
            e.printStackTrace();
        }

        // Quit the brower session
        driver.quit();
    }
}
