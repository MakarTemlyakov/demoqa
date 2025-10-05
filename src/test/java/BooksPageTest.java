import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import java.util.List;


public class BooksPageTest {
    private static WebDriver driver;
    private static final Logger logger = LogManager.getLogger();

    @BeforeAll
    public static void setup() {
        logger.info("Инициализация настроек для тестирования страницы...");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://demoqa.com/books");
    }

    @Test
    public void areBooksUiEqualsApi(){
        logger.info("Запуск теста для проверки списка кинг...");
        BooksPage bookPage = new BooksPage(driver);
        List<Book> apiBooks = bookPage.getListBooksByAPI();
        List<WebElement> books = bookPage.getBooks();

        for(Book book: apiBooks) {
            Assertions.assertEquals(book.getTitle(), bookPage.getTitleByBook(books.get(apiBooks.indexOf(book))));
        }

    }
}
