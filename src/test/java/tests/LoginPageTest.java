package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;

import java.time.Duration;


public class LoginPageTest {
    private static WebDriver driver;
    private static final Logger logger = LogManager.getLogger();

    @BeforeAll
    public static void setup() {
        logger.info("Инициализация настроек для тестирования страницы...");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://demoqa.com/login");
    }

    @Test
    public void checkLoginForm() {
        logger.info("Запуск теста для проверки авторизации...");
        LoginPage page = new LoginPage(driver);
        Assertions.assertTrue(page.checkLoginByCredentials("test12345@", "Test12345@"));
    }
}
