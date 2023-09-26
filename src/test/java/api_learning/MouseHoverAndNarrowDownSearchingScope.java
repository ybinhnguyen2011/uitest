package api_learning;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import url.Urls;

import java.util.List;

public class MouseHoverAndNarrowDownSearchingScope implements Urls {

    private final static By figureSel = By.className("figure");
    private final static By profileNameSel = By.cssSelector(".fifcaption h5");
    private final static By profileLinkSel = By.cssSelector(".fifcaption a");

    public static void main(String[] args) {

        // get a chrome session
        WebDriver driver = DriverFactory.getchrmomeDriver();



        try {
            // Navigate to the target page
            driver.get(baseUrlkuapp.concat(hoverlug));

            // tager parent element
            List<WebElement> figureElem = driver.findElements(figureSel);
            if(figureElem.isEmpty())
                throw new RuntimeException("There is no profile image displayed");

            // define Action objects
            Actions actions = new Actions(driver);

            for (WebElement figure : figureElem){
                WebElement profileNameElem = figure.findElement(profileNameSel);
                WebElement profileLinkElem = figure.findElement(profileLinkSel);

                // before mouse hover
                System.out.println(profileNameElem.getText() + profileNameElem.isDisplayed());
                System.out.println("Is profile link displaying" + profileLinkElem.isDisplayed());

                // Mouse hover
                actions.moveToElement(figure).perform();
            }

            //** Normal finding elements cách củ
//            List<WebElement> ligcaptionNamesElem = driver.findElements(By.cssSelector(".fifcaption h5"));
//            List<WebElement> ligcaptionHrefsElem = driver.findElements(By.cssSelector(".fifcaption a"));



        } catch (Exception e) {
            e.printStackTrace();
        }

        // Quit the brower session
        driver.quit();
    }
}
