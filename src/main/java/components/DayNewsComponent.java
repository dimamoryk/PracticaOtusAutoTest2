package components;

import annotations.Component;
import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@Component("css:.hdr__inner")
public class DayNewsComponent extends AbsComponent {

    private final WebDriver driver;

    @Inject
    public DayNewsComponent(WebDriver driver) {
        super(driver);
        this.driver = driver; // Явно назначаем значение полю driver
    }

    public WebElement getComponentEntry() {
        By bySelector = getByComponent(); // получаем селектор
        if (bySelector == null) {
            throw new IllegalStateException("No selector found for component entry"); // предотвращаем null-значение
        }
        return driver.findElement(bySelector); // ищем элемент по селектору
    }

    // Переопределяем getByComponent как protected
    protected By getByComponent() {
        return By.cssSelector(".hdr__inner"); // возвращаем правильный CSS-селектор
    }
}