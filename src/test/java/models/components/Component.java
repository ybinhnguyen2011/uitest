package models.components;

import net.bytebuddy.implementation.bytecode.Throw;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Constructor;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Component {

    protected WebDriver driver;
    protected WebElement component;

    protected WebDriverWait wait;


    public Component(WebDriver driver, WebElement component) {
        this.driver = driver;
        this.component = component;
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(15));
    }

    public WebElement getComponent() {
        return component;
    }

    public WebElement findElement(By by){
        return component.findElement(by);
    }

    public List<WebElement> findElements (By by){
        return component.findElements(by);
    }

    public <T extends Component> T findComponent(Class<T> componentClass, WebDriver driver){
        return findComponents(componentClass, driver).get(0);
    }

    public <T extends Component> List<T> findComponents(Class<T> componentClass, WebDriver driver){

        // Get components selector
        By componentSelector;
        try{
            componentSelector = getCompSelector(componentClass);
        }catch (Exception e){
            throw new IllegalArgumentException("[ERR] The component must have a css selector");
        }
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(componentSelector));
        List<WebElement> result = component.findElements(componentSelector);

        // Defind component class constructor parameters
        Class<?>[] params = new Class[]{WebDriver.class, WebElement.class};
        Constructor<T> constructor;
        try {
            constructor = componentClass.getConstructor(params);

        }catch (Exception e) {
            throw new IllegalArgumentException(
                    "[ERR] The component must have with parameter" + Arrays.toString(params));
        }

        // Convert all elements to components
        List<T> components = result.stream().map(webElement -> {
            try {
                return constructor.newInstance(driver, webElement);

            }catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());

        return components;
    }

    private By getCompSelector(Class<? extends Component> componentClass){
        if (componentClass.isAnnotationPresent(ComponentCssSelector.class)){
            return By.cssSelector(componentClass.getAnnotation(ComponentCssSelector.class).value());
        } else if (componentClass.isAnnotationPresent(ComponentXpathSelector.class)){
            return By.xpath(componentClass.getAnnotation(ComponentCssSelector.class).value());
        } else {
            throw new IllegalArgumentException("Component Class" + componentClass + " must have annotation"
                    + ComponentXpathSelector.class.getSimpleName() + " or " + ComponentCssSelector.class.getSimpleName());
        }
    }

    public void scrollUpToElement(WebElement element){
        scrollToElement("false", element);

    }
    public void scrollDownToElement(WebElement element){
        scrollToElement("true", element);

    }

    private void scrollToElement(String position, WebElement element){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollInToView("+ position +");",element);

    }

}
