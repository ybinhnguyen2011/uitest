package models.components.order;

import models.components.Component;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public abstract class ComputeEssentialComponent extends BaseItemDetailsComponent {

    private final static By allOptionsSel = By.cssSelector(".option-list input");

    public ComputeEssentialComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public abstract String selectProcessorType(String type);
    public abstract String selectRAMType(String type);

    public void unselectDefaultOptions() {
        List<WebElement> allOptionsElem = component.findElements(allOptionsSel);
        allOptionsElem.forEach(option ->{
            if (option.getAttribute("checked") != null){
                option.click();
            }
        });

    }

    public String selectHDD(String type){
        return selectCompOption(type);
    }

    public String selectOS(String type){
        return selectCompOption(type);
    }

    public String selectSoftware(String type){
        return selectCompOption(type);
    }

    protected String selectCompOption(String type) {
        String selectorStr = "//label[contains(text(), \"" + type + "\")]";
        By optionSel = By.xpath(selectorStr);
        WebElement optionElm = null;

        try {
            optionElm = component.findElement(optionSel);
        } catch (Exception ignored){}

        if (optionElm != null){
            optionElm.click();
            return optionElm.getText();
        }else{
            throw new RuntimeException("The option: " + type + " is not existing to select!");
        }


    }
}
