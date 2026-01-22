package pages;

import annotations.*;
import commons.AbsCommon;
import exceptions.PathNotFoundException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public abstract class AbsBasePage<T> extends AbsCommon {

    private final String baseurl = System.getProperty("base.url");

    @FindBy(tagName = "span a")
    protected WebElement header;

    public AbsBasePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    private String getPathWithData(String name, String... data) {
        Class<T> clazz = (Class<T>) this.getClass();
        Optional<UrlTemplate> urlTemplateOpt = Optional.empty();

        if (clazz.isAnnotationPresent(Urls.class)) {
            Urls urls = clazz.getDeclaredAnnotation(Urls.class);
            urlTemplateOpt = Arrays.stream(urls.urlTemplate())
                    .filter(template -> template.name().equals(name))
                    .findFirst();
        }

        if (urlTemplateOpt.isEmpty() && clazz.isAnnotationPresent(UrlTemplate.class)) {
            urlTemplateOpt = Optional.of(clazz.getDeclaredAnnotation(UrlTemplate.class));
        }

        return urlTemplateOpt.map(template -> replacePlaceholders(template.value(), data))
                .orElse("");
    }

    private String replacePlaceholders(String template, String... data) {
        for (int i = 0; i < data.length; i++) {
            template = template.replace(String.format("{$d}", i + 1), data[i]);
        }
        return template;
    }

    private String getPath() {
        Class<T> clazz = (Class<T>) this.getClass();
        if (clazz.isAnnotationPresent(Path.class)) {
            Path pathObj = clazz.getDeclaredAnnotation(Path.class);
            return pathObj.value();
        }
        throw new PathNotFoundException();
    }

    public T open() {
        driver.get(baseurl + getPath());
        return (T) this;
    }

    public T open(String name, String... data) {
        driver.get(baseurl + getPathWithData(name, data));
        return (T) this;
    }

    public T checkElementContainsText(WebElement element, String expectedText) {
        assertThat(element.getText()).contains(expectedText);
        return (T) this;
    }

    public T waitUntilElementIsVisible(WebElement element) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException ex) {
            throw new TimeoutException("Element '" + element + "' is still invisible.", ex);
        }
        return (T) this;
    }

    public T pageHeaderShowIdBySameUs(String header) {
        assertThat(this.header.getText()).as("Error").isEqualTo(header);
        return (T) this;
    }
}
