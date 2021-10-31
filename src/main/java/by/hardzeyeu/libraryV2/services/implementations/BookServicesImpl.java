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


    public Book getBook(int id) {
        return bookDAO.getBook(id);
    }

    public List<Book> getListOfBooks() {
        return bookDAO.getListOfBooks();
    }

    public void addBook(String title, String publisher, String author) {
        bookDAO.addBook(title, publisher, author);
    }

    public void updateBook(String title, String publisher, String author, int id) {
        bookDAO.updateBook(id, title, publisher, author);
    }

    public void removeBook(int id) {
        bookDAO.removeBook(id);
    }




}
