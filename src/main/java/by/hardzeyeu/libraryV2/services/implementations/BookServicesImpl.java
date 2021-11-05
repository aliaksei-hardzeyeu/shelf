package by.hardzeyeu.libraryV2.services.implementations;

import by.hardzeyeu.libraryV2.dao.BookDao;
import by.hardzeyeu.libraryV2.models.Book;
import by.hardzeyeu.libraryV2.services.BookService;

import java.sql.SQLException;
import java.util.List;

public class BookServicesImpl implements BookService {
    private static BookServicesImpl bookServicesImpl;
    private BookDao bookDAO;


    private BookServicesImpl() {
        bookDAO = new BookDao();
    }

    public BookServicesImpl(BookDao bookDao) {
        this.bookDAO = bookDao;
    }

    public static BookServicesImpl getInstance() {
        if (bookServicesImpl == null)
            bookServicesImpl = new BookServicesImpl();
        return bookServicesImpl;
    }


    public Book getBook(int id) {
        try {
            return bookDAO.getBook(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public List<Book> getListOfBooks() {
        try {
            return bookDAO.getListOfBooks();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void addBook(String title, String publisher, String author) {
        bookDAO.addBook(title, publisher, author);
    }

    public void updateBook(String title,  String author, String publisher, int id) {
        bookDAO.updateBook(id, title, author, publisher);
    }

    public void removeBook(int id) {
        bookDAO.removeBook(id);
    }




}
