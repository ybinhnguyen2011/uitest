package test_flows.computer;

import models.components.cart.TotalComponent;
import models.components.order.ComputeEssentialComponent;
import models.pages.ComputerItemDetailsPage;
import models.pages.ShoppingCartPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import test_data.computer.ComputerData;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderComputerFlow<T extends ComputeEssentialComponent> {

    private final WebDriver driver;
    private final Class<T> computerEssentialComponent;
    private final ComputerData computerData;
    private final int quantity;
    private double totalItemPrice;

    public OrderComputerFlow(WebDriver driver, Class<T> computerEssentialComponent, ComputerData computerData) {
        this.driver = driver;
        this.computerEssentialComponent = computerEssentialComponent;
        this.computerData = computerData;
        this.quantity = 1;
    }
    public OrderComputerFlow(WebDriver driver, Class<T> computerEssentialComponent, ComputerData computerData, int quantity) {
        this.driver = driver;
        this.computerEssentialComponent = computerEssentialComponent;
        this.computerData = computerData;
        this.quantity = quantity;
    }

    public  void buildCompSpecAndAddtoCart(){
        ComputerItemDetailsPage computerItemDetailsPage = new ComputerItemDetailsPage(driver);
        T computerEssentialComp = computerItemDetailsPage.computerComp(computerEssentialComponent);

        // Unselect all default options
        computerEssentialComp.unselectDefaultOptions();

        String processorFullStr = computerEssentialComp.selectProcessorType(computerData.getProcessorType());
        double processorAdditionalPrice = extractAdditionalPrice(processorFullStr);
        System.out.println("processorAdditionalPrice: " + processorAdditionalPrice);

        String ramFullStr = computerEssentialComp.selectRAMType(computerData.getRam());
        double ramAdditionalPrice = extractAdditionalPrice(ramFullStr);
        System.out.println("ramAdditionalPrice: " + ramAdditionalPrice);

        String hddFullStr = computerEssentialComp.selectHDD(computerData.getHdd());
        double additionalHddPrice = extractAdditionalPrice(hddFullStr);
        System.out.println("additionalHddPrice: " + additionalHddPrice);

        double additionalOsPrice = 0;
        if (computerData.getOs() != null) {
            String fullOsStr = computerEssentialComp.selectOS(computerData.getOs());
            additionalOsPrice = extractAdditionalPrice(fullOsStr);
        }
        System.out.println("additionalOsPrice: " + additionalOsPrice );

        String softwareFullStr = computerEssentialComp.selectSoftware(computerData.getSoftware());
        double additionalSoftwarePrice = extractAdditionalPrice(softwareFullStr);

        // Calculate item's price and add it cart
        double basePrice = computerEssentialComp.productPrice();
        double allAdditionalPrice = processorAdditionalPrice + ramAdditionalPrice + additionalHddPrice + additionalOsPrice
                + additionalSoftwarePrice;
        totalItemPrice = (basePrice + allAdditionalPrice) * quantity;

        // And  add to Cart
        computerEssentialComp.clickOnAddToCartBtn();
        computerEssentialComp.waitUntilItemAddedToCart();


        System.out.println(processorAdditionalPrice);
        System.out.println(ramAdditionalPrice);
        System.out.println(additionalHddPrice);
        System.out.println(additionalOsPrice);
        System.out.println(additionalSoftwarePrice);
        System.out.println(basePrice);
        System.out.println("totalItemPrice" + totalItemPrice);

        // Then Navigate to shopping cart
        computerItemDetailsPage.headerComp().clickOnShoppingCartLink();



        try {
            Thread.sleep(5000);

        }catch (Exception e){}
    }

    private double extractAdditionalPrice(String itemStr){
        double price = 0;
        Pattern pattern = Pattern.compile("\\[(.*?)\\]");
        Matcher matcher = pattern.matcher(itemStr);
        if (matcher.find()) {
            price = Double.parseDouble(matcher.group(1).replaceAll("[-+]", ""));
        }
        return price;
    }

    public void verifyShoppingCartPage(){
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        TotalComponent totalComp = shoppingCartPage.totalComp();
        Map<String, Double> priceCategories = totalComp.priceCategories();
        for (String priceType : priceCategories.keySet()) {
            System.out.println("priceType: " + priceType);
            System.out.println("priceValue: " + priceCategories.get(priceType));

            
        }

    }
}
