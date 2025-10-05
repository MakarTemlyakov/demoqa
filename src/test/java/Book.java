import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {
    @JsonProperty("isbn")
    private String isbn;

    @JsonProperty("title")
    private String title;

    @JsonProperty("subTitle")
    private String subTitle;

    @JsonProperty("author")
    private String author;

    @JsonProperty("publish_date")
    private String publishDate;

    @JsonProperty("publisher")
    private String publisher;

    @JsonProperty("pages")
    private int pages;

    @JsonProperty("description")
    public String description;

    @JsonProperty("website")
    public String website;

    public Book(){}

    public String getIsbn(){
        return this.isbn;
    }

    public void setIsbn(String setIsbn){
        this.isbn = setIsbn;
    }

    public String getTitle(){
        return this.title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String subTitle(){
        return this.subTitle;
    }

    public void setSubTitle(String subTitle){
        this.subTitle = subTitle;
    }

    public String author(){
        return this.author;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public String publishDate(){
        return this.publishDate;
    }

    public void  setPublishDate(String publishDate){
        this.publishDate = publishDate;
    }

    public String publisher(){
        return this.publisher;
    }

    public void setPublisher(String publisher){
        this.publisher = publisher;
    }

    public int getPages(){
        return this.pages;
    }

    public void setPages(int pages){
        this.pages = pages;
    }

    public String description(){
        return this.description;
    }

    public void description(String description){
        this.description = description;
    }

    public String website(){
        return this.website;
    }

    public void website(String website){
        this.website = website;
    }
}
