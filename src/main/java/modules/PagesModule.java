package modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import components.popups.AuthPopup;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MainPage;

public class PagesModule extends AbstractModule {

    private final WebDriver driver;

    public PagesModule(WebDriver driver) {
        this.driver = driver;
    }

    @Provides
    @Singleton
    public MainPage getMainPage() {
        return new MainPage(driver);
    }

    protected void configure() {
        bind(WebDriver.class).toProvider(() -> new ChromeDriver());
        bind(MainPage.class).toProvider(() -> new MainPage(driver));
        bind(AuthPopup.class).toProvider(() -> new AuthPopup(driver));
    }
}
