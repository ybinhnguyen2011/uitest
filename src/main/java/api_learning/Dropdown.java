package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import support.ui.SelectEx;
import url.Urls;

import java.util.List;

public class Dropdown implements Urls {
    public static void main(String[] args) {

        // get a chrome session
        WebDriver driver = DriverFactory.getchrmomeDriver();

        try {
            // Navigate to the target page
            driver.get(baseUrlkuapp.concat(dropdown));

            // Dropdown locator and element
            By dropdownSel = By.id("dropdown");
            WebElement dropdownEln = driver.findElement(dropdownSel);

            // select the dropdown
//            Select select = new Select(dropdownEln);
            SelectEx select = new SelectEx(dropdownEln);

            // select by visible text | option 1
            select.selectOption1();
            Thread.sleep(2000);

            // select by index
            select.selectByIndex(2);
            Thread.sleep(2000);

            // select by value
            select.selectByValue("1");
            Thread.sleep(2000);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Quit the brower session
        driver.quit();
    }
}
