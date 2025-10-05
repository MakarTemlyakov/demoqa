package model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class BookResponse {

    @JsonProperty("books")
    private List<Book> books;

    public BookResponse(){}

    public List<Book> getBooks(){
        return this.books;
    }

    public void setBooks(List<Book> books){
        this.books = books;
    }

}
