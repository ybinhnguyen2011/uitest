package models.components;

import models.pages.LoginPageMod01;

import java.lang.reflect.Constructor;

import static url.Urls.baseUrlkuapp;
import static url.Urls.loginlughero;

public class AnnotationTest {

    public <T> void getComponentCssSelector(Class<T> componentClass) {

        try {
            String cssSelector = componentClass.getAnnotation(ComponentCssSelector.class).value();
            System.out.println(cssSelector);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new AnnotationTest().getComponentCssSelector(LoginFormComponent.class);
    }
}
