package factory.settings;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;

public class ChromeSettings implements Isettings {

    @Override
    public AbstractDriverOptions<?> settings() { // Добавлен тип параметра <?>
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-fullscreen");
        return chromeOptions; // Возвращаем объект ChromeOptions
    }
}