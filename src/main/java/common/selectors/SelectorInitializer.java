package common.selectors;

import common.Interfaces.SelectorID;
import common.Interfaces.SelectorXPath;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.Field;

public class SelectorInitializer {

    @SneakyThrows
    public static void initSelectors(WebDriver webDriver, Object pageObject) {
        for (Field field : pageObject.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(SelectorXPath.class)) {
                SelectorXPath fieldAnnotation = field.getAnnotation(SelectorXPath.class);
                field.setAccessible(true);
                Object selector = field
                        .getType()
                        .getConstructor(WebDriver.class, String.class, String.class)
                        .newInstance(webDriver, fieldAnnotation.selectorName(), fieldAnnotation.elementXPath());
                field.set(pageObject,selector);
            }
            if (field.isAnnotationPresent(SelectorID.class)) {
                SelectorID fieldAnnotation = field.getAnnotation(SelectorID.class);
                field.setAccessible(true);
                Object selector = field
                        .getType()
                        .getConstructor(WebDriver.class, String.class, String.class)
                        .newInstance(webDriver, fieldAnnotation.selectorName(), fieldAnnotation.elementID());
                field.set(pageObject,selector);
            }

        }
    }
}
