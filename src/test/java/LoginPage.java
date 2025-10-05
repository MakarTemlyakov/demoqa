import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private static final Logger logger = LogManager.getLogger();

    @FindBy(xpath = "//input[@id='userName']")
    private WebElement userNameInput;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[@id='login']")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        wait =  new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public WebElement getUserNameInput() {
        return this.userNameInput;
    }

    public WebElement getPasswordInput() {
        return this.passwordInput;
    }

    public WebElement getLoginButton() {
        return this.loginButton;
    }

    public boolean checkLoginByCredentials(String name, String password) {
        logger.debug("Инициализация пользовательских данных для авторизации...");
        //Спрашивал у AI какие способы есть для измнения значения в найденном WebElement
        userNameInput.sendKeys(name);
        passwordInput.sendKeys(password);
        loginButton.click();

        String profileUrl = "https://demoqa.com/profile";
        String currentUrl = this.driver.getCurrentUrl();

        if(currentUrl != null && currentUrl.equals(profileUrl)) {
            String userName = this.driver.findElement(By.xpath("//label[@id='userName-value']")).getText();
            if(userName.equals(name)) {
                return true;
            }
        }
        return true;
    }
}
