package models.components;

import org.openqa.selenium.WebDriver;

public abstract class LoginPage {

    public void login(){
        System.out.println(username());
        System.out.println(passwork());
        System.out.println(loginBtn());

    }

    public void verifyLoginSuccess(){
        System.out.println("Verifying Dashboard display");

    }

    public abstract String username();
    public abstract String passwork();
    public abstract String loginBtn();
}