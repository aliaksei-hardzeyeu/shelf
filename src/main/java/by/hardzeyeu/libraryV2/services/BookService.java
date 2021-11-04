package by.hardzeyeu.libraryV2.services;

import by.hardzeyeu.libraryV2.models.Book;

import java.sql.SQLException;
import java.util.List;

public interface BookService {

    /**
     * Gets book from DB by id
     *
     * @param id
     * @return book
     * @throws SQLException
     */

    Book getBook(int id) throws SQLException;

    /**
     * Gets entire list of books from DB
     *
     * @return List of books
     */

    List<Book> getListOfBooks();

    /**
     *
     *
     * @param title
     * @param publisher
     * @param author
     */

    void addBook(String title, String publisher, String author);

    void updateBook(String title, String publisher, String author, int id);

    void removeBook(int id);



}
