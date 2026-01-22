package components.popups;

import commons.AbsCommon;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class AuthPopup extends AbsCommon implements IPopup<AuthPopup> {

    private final WebDriver driver;

    public AuthPopup(WebDriver driver) {
        super(driver);
        this.driver = driver; // Гарантированно инициализируем driver
        PageFactory.initElements(driver, this); // Инициализируем элементы страницы
    }

    @Override
    public AuthPopup popupShouldBeVisible() {
        boolean visible = findElementByLocator(By.id("auth-popup")).isDisplayed();
        if (!visible) {
            throw new AssertionError("Авторизационный попап не отображается на странице");
        }
        return this;
    }

    @Override
    public AuthPopup popupShouldNotBeVisible() {
        boolean present = false;
        try {
            present = findElementByLocator(By.id("auth-popup")).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException ignored) {}
        if (present) {
            throw new AssertionError("Авторизационный попап отображается на странице, хотя должен быть скрыт");
        }
        return this;
    }

    // Основной метод для поиска элементов
    protected WebElement findElementByLocator(By locator) {
        return driver.findElement(locator);
    }
}