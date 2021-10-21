package by.hardzeyeu.libraryV2.services.implementations;

import by.hardzeyeu.libraryV2.dao.BookDAO;
import by.hardzeyeu.libraryV2.models.Book;
import by.hardzeyeu.libraryV2.services.BookService;

import java.util.List;

public class BookServicesImpl implements BookService {
    private static BookServicesImpl bookServicesImpl;
    private BookDAO bookDAO;

    private BookServicesImpl() {
        bookDAO = new BookDAO();
    }

    public static BookServicesImpl getInstance() {
        if (bookServicesImpl == null)
            bookServicesImpl = new BookServicesImpl();
        return bookServicesImpl;
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
