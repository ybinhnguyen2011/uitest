package test_flows.computer;

import models.components.order.ComputeEssentialComponent;
import models.pages.ComputerItemDetailsPage;
import org.openqa.selenium.WebDriver;
import test_data.computer.ComputerData;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderComputerFlow<T extends ComputeEssentialComponent> {

    private final WebDriver driver;
    private final Class<T> computerEssentialComponent;
    private final ComputerData computerData;

    public OrderComputerFlow(WebDriver driver, Class<T> computerEssentialComponent, ComputerData computerData) {
        this.driver = driver;
        this.computerEssentialComponent = computerEssentialComponent;
        this.computerData = computerData;
    }

    public  void buildCompSpecAndAddtoCart(){
        ComputerItemDetailsPage computerItemDetailsPage = new ComputerItemDetailsPage(driver);
        T computerEssentialComp = computerItemDetailsPage.computerComp(computerEssentialComponent);

        String processorFullStr = computerEssentialComp.selectProcessorType(computerData.getProcessorType());
        double processorAdditionalPrice = extractAdditionalPrice(processorFullStr);
        System.out.println("processorAdditionalPrice: " + processorAdditionalPrice);

        String ramFullStr = computerEssentialComp.selectRAMType(computerData.getRam());
        double ramAdditionalPrice = extractAdditionalPrice(ramFullStr);
        System.out.println("ramAdditionalPrice: " + ramAdditionalPrice);

        String hddFullStr = computerEssentialComp.selectHDD(computerData.getHdd());
        double additionalHDDPrice = extractAdditionalPrice(hddFullStr);
        System.out.println("additionalHDDPrice: " + additionalHDDPrice);

        double additionalOsPrice = 0;
        if (computerData.getOs() != null) {
            String fullOsStr = computerEssentialComp.selectOS(computerData.getOs());
            additionalOsPrice = extractAdditionalPrice(fullOsStr);
        }
        System.out.println("additionalOsPrice: " + additionalOsPrice );

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
}
