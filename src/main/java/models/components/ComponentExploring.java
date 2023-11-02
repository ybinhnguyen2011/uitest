package models.components;

import driver.DriverFactory;
import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.Constructor;

public class ComponentExploring {

    // Giống như tạo cái khung, nhưng khi nào đúc, đúc như thế nào thì tự quyết định
    public <T extends LoginPage> void login(Class<T> loginPageClass) {
        Class<?>[] paramerters = new Class[]{};

        try {
            Constructor<T> constructor = loginPageClass.getConstructor(paramerters);
            T loginPageObj = constructor.newInstance();
            loginPageObj.login();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ComponentExploring().login(InternalLoginPage.class);
        new ComponentExploring().login(ExternalLoginPage.class);
    }
}
