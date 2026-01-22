package extensions;

import com.google.inject.Guice;
import factory.WebDriverFactory;
import modules.PagesModule;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;

public class UiExtension implements BeforeEachCallback, BeforeAllCallback, AfterEachCallback {

    private WebDriver driver;

    private final WebDriverFactory webDriverFactory = new WebDriverFactory(); // Сделали поле final


    @Override
    public void afterEach(ExtensionContext context) {
        if (driver != null) {
            driver.quit();
        }
    }

    @Override
    public void beforeEach(ExtensionContext context) {
        driver = webDriverFactory.create();
        Guice.createInjector(new PagesModule(driver)).injectMembers(context.getTestInstance().get());
    }

    @Override
    public void beforeAll(ExtensionContext context) {
        webDriverFactory.init();
    }
}