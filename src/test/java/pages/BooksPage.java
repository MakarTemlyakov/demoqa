package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class BooksPage {

    @FindBy(xpath = "//div[contains(@class,'ReactTable')]//div[@class='rt-tr -odd' or @class='rt-tr -even']")
    private List<WebElement> books;

    public List<WebElement> getBooks() {
        return this.books;
    }

    public BooksPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public  String getTitleByBook(WebElement book) {
        return book.findElement(By.xpath(".//div[@class='action-buttons']//span[starts-with(@id, 'see-book-')]")).getText();
    }
}
