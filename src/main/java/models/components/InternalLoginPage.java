package models.components;

import org.openqa.selenium.WebDriver;

public class InternalLoginPage extends LoginPage {

    @Override
    public String username() {
        return "InternalLoginPage | username";
    }

    @Override
    public String passwork() {
        return "InternalLoginPage | passwork";
    }

    @Override
    public String loginBtn() {
        return "InternalLoginPage | loginBtn";
    }
}
