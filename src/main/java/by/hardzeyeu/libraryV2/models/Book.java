package by.hardzeyeu.libraryV2.models;

import java.time.LocalDate;

public class Book {
    private int bookId;
    private String title;
    private String authors;
    private String publisher;
    private String genres;
    private int pageCount;
    private String isbn;
    private String des;
    private LocalDate publDate;
    private String status;
    private int amount;

    public Book() {
    }


    /**
     * Constructor isn`t complete!!!!!
     * @param title
     * @param authors
     * @param publisher
     * @param genres
     * @param pageCount
     * @param isbn
     * @param des
     * @param publDate
     */
    //constructor without id!
    public Book(String title, String authors, String publisher, String genres,
                int pageCount, String isbn, String des, LocalDate publDate, int amount) {
        this.title = title;
        this.authors = authors;
        this.publisher = publisher;
        this.genres = genres;
        this.pageCount = pageCount;
        this.isbn = isbn;
        this.des = des;
        this.publDate = publDate;
        this.amount = amount;
    }


    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
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

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public LocalDate getPublDate() {
        return publDate;
    }

    public void setPublDate(LocalDate publDate) {
        this.publDate = publDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return  "book_id= " + bookId + "| title = " + title +"| authors=" + authors + "| publisher= " + publisher + "| genre= " + genres + "| pageCount= " + pageCount +
                "| isbn= " + isbn + "| description=" + des + "| publDate= " + publDate + "| status= " + status +
                "| amount= " + amount;
    }
}
