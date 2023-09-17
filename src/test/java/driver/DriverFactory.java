package driver;

import org.apache.commons.exec.OS;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {
    public static WebDriver getchrmomeDriver() {
        String currentProjectLocation = System.getProperty("user.dir");
        String chromeDriverLocation = "";
        if (OS.isFamilyMac()) {
            chromeDriverLocation = currentProjectLocation + "/src/test/resources/drivers/chromedriver.exe";

        }
        if (OS.isFamilyWindows()) {
            chromeDriverLocation = currentProjectLocation + "\\src\\test\\resources\\drivers\\chromedriver.exe";

        }
        if (chromeDriverLocation.isEmpty()) {
            throw new IllegalStateException("'Can't detect OS type!");
        }
        System.setProperty("webdriver.chrome.driver", chromeDriverLocation);

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--incognito-");

        return new ChromeDriver(chromeOptions);
    }
}
