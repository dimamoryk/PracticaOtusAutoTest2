package pages;

import annotations.Path;
import org.openqa.selenium.WebDriver;

@Path("/news")
public class NewsPage extends AbsBasePage<NewsPage>{

    public NewsPage(WebDriver driver){
        super(driver);
    }
}
