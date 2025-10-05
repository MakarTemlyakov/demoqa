import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;

public class BooksPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private static final Logger logger = LogManager.getLogger();

    @FindBy(xpath = "//div[contains(@class,'ReactTable')]//div[@class='rt-tr -odd' or @class='rt-tr -even']")
    private List<WebElement> books;

    public List<WebElement> getBooks() {
        return this.books;
    }


    public BooksPage(WebDriver driver) {
        this.driver = driver;
        wait =  new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    private List<Book> getParsedData(String response) throws Exception {
        logger.debug("Парсинг полученных API данных...");
        try {
            ObjectMapper mapper = new ObjectMapper();
            //Спросил у ai как спрарсить данные в класс без дополнительнных классов оберткок для поля "books"
            JsonNode root = mapper.readTree(response);
            JsonNode booksArray = root.get("books");
            return mapper.readValue(booksArray.toString(), new TypeReference<>(){});
        }
        catch (JsonProcessingException error){
            logger.error("Парсинг данных завершился некорректно");
            throw new RuntimeException("Ошибка парсинга данных", error);
        }
    }

    public List<Book> getListBooksByAPI() {
        try {
            logger.debug("Получение данных через API");
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request =  HttpRequest
                    .newBuilder()
                    .uri(URI.create("https://demoqa.com/BookStore/v1/Books"))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return getParsedData(response.body());

        } catch (Exception e) {
            logger.error("Ошибка при выполнении HTTP запроса");
            throw new RuntimeException("Не удалось получить данные из API", e);

        }
    }

    public  String getTitleByBook(WebElement book) {
        return book.findElement(By.xpath(".//div[@class='action-buttons']//span[starts-with(@id, 'see-book-')]")).getText();
    }
}
