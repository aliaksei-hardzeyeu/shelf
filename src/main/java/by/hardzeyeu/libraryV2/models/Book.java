package by.hardzeyeu.libraryV2.models;

import java.time.LocalDate;
import java.util.Objects;

public class Book {
    private int id;
    private String title;
    private String author;
    private String publisher;


    public Book() {
    }


    public Book(String title, String author, String publisher) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;

    }

    public Book(int id, String title, String author, String publisher) {
        this.id = id;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id && Objects.equals(title, book.title) && Objects.equals(author, book.author) && Objects.equals(publisher, book.publisher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, publisher);
    }


    @Override
    public String toString() {
        return  "id= " + id + "| title = " + title +"| authors=" + author + "| publisher= " + publisher;
    }
}
