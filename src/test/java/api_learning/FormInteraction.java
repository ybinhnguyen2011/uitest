package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FormInteraction {
    public static void main(String[] args) {
        // get a chrome session
        WebDriver driver = DriverFactory.getchrmomeDriver();

        // Navigate to the target page
        driver.get("https://beta.aloline.vn/auth");

        // Find elements
        WebElement usernameElem = driver.findElement(By.xpath("//input[contains(@placeholder,'Số điện thoại')]"));
//        WebElement passwordElem = driver.findElement(By.xpath("//input[@placeholder='Mật khẩu']"));
//        WebElement loginElem = driver.findElement(By.xpath("//button[contains(text(),'Đăng nhập')]"));

        // Interaction
        usernameElem.sendKeys("0336400645");
//        passwordElem.sendKeys("1111");
//        loginElem.click();

        // DEbug purpose only
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Quit the brower session
        driver.quit();
    }
}
