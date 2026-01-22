package pages;

import annotations.UrlTemplate;
import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;



@UrlTemplate(
        value = "/article/{1}"
)
public class ArticlePage extends AbsBasePage<ArticlePage> {

    private final WebDriver driver;

    @Inject // добавляем аннотацию @Inject
    public ArticlePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
}
