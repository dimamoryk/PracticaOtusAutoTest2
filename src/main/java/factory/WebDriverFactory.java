package factory;

import exceptions.BrowserNotSupportException;
import factory.settings.ChromeSettings;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Locale;

public class WebDriverFactory {

    private final String browser = System.getProperty("browser.name")
            .toLowerCase(Locale.ROOT);

    public WebDriver create() {
        if ("chrome".equals(browser)) {
            WebDriverManager.chromedriver().setup();
            return new
                    ChromeDriver((ChromeOptions) new ChromeSettings().settings());
        }
        throw new BrowserNotSupportException(browser);
    }

    public void init() {
        if ("chrome".equals(browser)) {
            WebDriverManager.chromedriver().setup();
        } else {
            throw new BrowserNotSupportException(browser);
        }
    }
}