package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import url.Urls;

public class Iframe implements Urls {
    public static void main(String[] args) {

        // get a chrome session
        WebDriver driver = DriverFactory.getchrmomeDriver();



        try {
            // Navigate to the target page
            driver.get(baseUrlkuapp.concat(iframelug));

            // Locator the iframe
            By iFrameSel = By.cssSelector("[id$='ifr']");
            WebElement iFrameElem = driver.findElement(iFrameSel);

            // Switch to the iframe
            driver.switchTo().frame(iFrameElem);

            // Locator element inside the iframe
            WebElement editorInputElem = driver.findElement(By.id("tinymce"));
            editorInputElem.click();
            editorInputElem.clear();
            editorInputElem.sendKeys("This is the new text value");
            Thread.sleep(2000);

            // Switch back parent iframe
            driver.switchTo().defaultContent();
            driver.findElement(By.linkText("Elemental Selenium")).click();
            Thread.sleep(2000);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Quit the brower session
        driver.quit();
    }
}
