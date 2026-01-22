package components;

import annotations.Component;
import commons.AbsCommon;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class AbsComponent extends AbsCommon {

    {
        assertThat(waiter.waitForCondition
                (ExpectedConditions
                        .visibilityOfAllElementsLocatedBy(getByComponent())
                )).as("Error")
                .isTrue();
    }

    public AbsComponent(WebDriver driver) {
        super(driver);
    }

    private By getByComponent() {
        Class<?> clazz = getClass();

        if (clazz.isAnnotationPresent(Component.class)) {
            Component component = clazz.getAnnotation(Component.class);
            String[] componentVal = component.value().split(":");
            if ("css".equals(componentVal[0])) {
                return By.cssSelector(componentVal[1]);
            }
        }
        return null;
    }

    public WebElement getComponentEntry() {
        return $(getByComponent());
    }
}
