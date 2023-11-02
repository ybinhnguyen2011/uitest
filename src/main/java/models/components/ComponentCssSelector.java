package models.components;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.RUNTIME) // Thời gian khi nào, mình chọn khi Run nó
@Target(value = {ElementType.TYPE}) // Nơi muốn apply anotation ở đâu => Chọn method trên đầu của Class
public @interface ComponentCssSelector {
    String value();

}
