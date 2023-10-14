package models.components.order;

import models.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

@ComponentCssSelector(".product-essential")
public class StandardComputerComponent extends ComputeEssentialComponent{

    private static final By productAttributeSel = By.cssSelector("select[id^=\"product_attribute\"]");

    public StandardComputerComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    @Override
    public String selectProcessorType(String type) {

        final int PROCESSOR_DROPDOWN_INDEX = 0;
        WebElement processDropdownElem =component.findElements(productAttributeSel).get(PROCESSOR_DROPDOWN_INDEX);
        return selectOption(processDropdownElem, type);

    }

    @Override
    public String selectRAMType(String type) {
        final int RAM_DROPDOWN_INDEX = 1;
        WebElement ramDropdownElem =component.findElements(productAttributeSel).get(RAM_DROPDOWN_INDEX);
        return selectOption(ramDropdownElem, type);
    }

    private String selectOption(WebElement dropdownElem, String type){
        Select select = new Select(dropdownElem);
        List<WebElement> allOptions = select.getOptions();
        String fullStrOption = null;

        for (WebElement option : allOptions) {
            String currentOptionText = option.getText();
            String optionTextWindownSpace =currentOptionText.trim().replace(",", " ");
            if (optionTextWindownSpace.startsWith(type)){
                fullStrOption = currentOptionText;
                break;
            }
        }
        if (fullStrOption == null){
            throw new RuntimeException("[ERROR] the option " + type + " is not exist to select");
        }

        select.selectByVisibleText(fullStrOption);
        return fullStrOption;

    }

}
