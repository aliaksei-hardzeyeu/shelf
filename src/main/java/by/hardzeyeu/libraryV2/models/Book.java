package by.hardzeyeu.libraryV2.models;

import java.time.LocalDate;

public class Book {
    private int id;
    private String title;
    private String author;
    private String publisher;


    public Book() {
    }


    public Book(String title, String author, String publisher, String genres,
                int pageCount, String isbn, String des, LocalDate publDate, int amount) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;

    }


    public int getId() {
        return id;
    }

    public void setId(int bookId) {
        this.id = bookId;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    @Override
    public String toString() {
        return  "book_id= " + id + "| title = " + title +"| authors=" + author + "| publisher= " + publisher;
    }
}
