package common.selectors;

import common.Interfaces.Selector;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.Field;

public class SelectorInitializer {

    @SneakyThrows
    public static void initSelectors(WebDriver webDriver, Object page) {
        for (Field field : page.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Selector.class)) {
                Selector fieldAnnotation = field.getAnnotation(Selector.class);
                field.setAccessible(true);
                Object selector = field
                        .getType()
                        .getConstructor(WebDriver.class, String.class, String.class)
                        .newInstance(webDriver, fieldAnnotation.selectorName(), fieldAnnotation.elementPath());
                field.set(page,selector);
            }

        }
    }
}
