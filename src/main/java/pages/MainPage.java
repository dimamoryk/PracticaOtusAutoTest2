package pages;

import annotations.Path;
import extensions.UiExtension;
import jakarta.inject.Inject;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import components.DayNewsComponent;

import java.util.List;

@ExtendWith(UiExtension.class)
@Path("/")
public class MainPage extends AbsBasePage<MainPage> {

    @Inject
    protected ArticlePage articlePage;

    @Inject
    protected DayNewsComponent dayNewsComponent;

    @FindBy(css = "hdr__inner")
    protected List<WebElement> articles;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public String getPhotoTitleByIndex(int index) {
        return articles.get(index - 1).getText();
    }

    public ArticlePage clickArticleByTitle(String title) {
        this.clickElementByPredicate.accept(articles,
                (WebElement element) -> element.getText().equals(title));
        return articlePage;
    }

    // Метод для получения компонента прямо из страницы
    public WebElement getDayNewsComponentEntry() {
        return dayNewsComponent.getComponentEntry();
    }
}