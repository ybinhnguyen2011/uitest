package models.components.global.header;

import models.components.Component;
import models.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

@ComponentCssSelector(value = ".top-menu")
public class TopMenuComponent extends Component {

    public TopMenuComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public List<MainCatItem> mainItemsElem() {
        return findComponents(MainCatItem.class, driver);
    }

    @ComponentCssSelector(value = ".top-menu > li")
    public static class MainCatItem extends Component {

        public MainCatItem(WebDriver driver, WebElement component) {
            super(driver, component);
        }

        public WebElement catItemLinkElem() {
            return component.findElement(By.tagName("a"));
        }
        public List<CatItemComponent> catItemComps() {
            Actions actions = new Actions(driver);
            actions.moveToElement(component).perform();
            return findComponents(CatItemComponent.class, driver);
        }
    }

    @ComponentCssSelector(value = ".sublist li a")
    public static class CatItemComponent extends Component {

        public CatItemComponent(WebDriver driver, WebElement component) {
            super(driver, component);
        }
    }
}
