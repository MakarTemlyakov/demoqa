package services;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Book;
import model.BookResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class BookService {
    private static final Logger logger = LogManager.getLogger();
    private final HttpClient client;
    private final ObjectMapper mapper;

    public BookService() {
        mapper = new ObjectMapper();
        client = HttpClient.newHttpClient();
    }

    public List<Book> getListBooksByAPI() {
        try {
            logger.debug("Получение данных через API");
            HttpRequest request =  HttpRequest
                    .newBuilder()
                    .uri(URI.create("https://demoqa.com/BookStore/v1/Books"))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            BookResponse bookResponse = mapper.readValue(response.body(), BookResponse.class);

            return bookResponse.getBooks();

        } catch (Exception e) {
            logger.error("Ошибка при выполнении HTTP запроса");
            throw new RuntimeException("Не удалось получить данные из API", e);
        }
    }
}


