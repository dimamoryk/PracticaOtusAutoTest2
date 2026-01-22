package main;

import components.popups.AuthPopup;
import extensions.UiExtension;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebElement;
import pages.MainPage;

@ExtendWith(UiExtension.class)
public class MainPageTest {

    @Inject
    protected MainPage mainPage;

    protected AuthPopup authPopup;

    @Test
    public void testPopupVisibility() {
        // Открываем страницу
        mainPage.open();

        // Проверяем, что попап изначально скрыт
        authPopup.popupShouldNotBeVisible();

        // Выполняем действие, открывающее попап (если надо)
        // Например: mainPage.triggerPopupAction();

        // Проверяем, что попап появился
        authPopup.popupShouldBeVisible();
    }

    @Test
    public void clickArticleTitle() {
        // Открываем страницу
        mainPage.open();

        // Получаем компонент
        WebElement component =
                mainPage.getDayNewsComponentEntry();

        // ПРОВЕРКА КОМПОНЕНТА
        if (component == null || component
                .getCssValue("display")
                .equals("none")) {

            throw new
                    AssertionError("Элемент компонента не найден или скрыт!");
        }

        mainPage.checkElementContainsText(component, "Expected Text");
        mainPage.waitUntilElementIsVisible(component);

        String title =
                mainPage.getPhotoTitleByIndex(1);
        mainPage.clickArticleByTitle(title)
                .pageHeaderShowIdBySameUs(title);
    }
}