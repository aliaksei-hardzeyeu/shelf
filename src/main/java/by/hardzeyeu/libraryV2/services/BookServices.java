package by.hardzeyeu.libraryV2.services;

import by.hardzeyeu.libraryV2.DAO.BookDAO;
import by.hardzeyeu.libraryV2.DAO.implementation.BookDAOImpl;
import by.hardzeyeu.libraryV2.connection.C3P0DataSource;
import by.hardzeyeu.libraryV2.models.Book;

import java.util.List;

public class BookServices {
    private static BookServices bookServices;
    private BookDAO bookDAO;


    private BookServices() {
        bookDAO = new BookDAOImpl();
    }

    public static BookServices getInstance() {
        if (bookServices == null)
            bookServices = new BookServices();
        return bookServices;
    }


    public Book getBook(int bookId) {
        return bookDAO.getBook(bookId);
    }

    public List<Book> getListOfBooks() {
        return bookDAO.getListOfBooks();
    }

    public void addBook(String title, String publisher, int page_count, String isbn, String des, String publDate,
                   String authors, String genres, int amount) {
        bookDAO.addBook(title, publisher, page_count, isbn, des, publDate, authors, genres, amount);
    }

    public void updateBook(String title, String publisher, int page_count, String isbn, String des, String publDate,
                           String authors, String genres, int amount, int book_id) {
        bookDAO.updateBook(title, publisher, page_count, isbn, des, publDate, authors, genres, amount, book_id);
    }

    public void removeBook(int book_id) {
        bookDAO.removeBook(book_id);
    }

//    todo
    public void countStatus() {

    }

}
