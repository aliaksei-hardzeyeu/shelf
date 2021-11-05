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

    Book getBook(int id);

    /**
     * Gets entire list of books from DB
     *
     * @return List of books
     */

    List<Book> getListOfBooks();

    /**
     * Adds book with given params
     *
     * @param title
     * @param publisher
     * @param author
     */

    void addBook(String title, String publisher, String author);


    /**
     * Updates existing book according to new parameters
     *
     * @param title
     * @param publisher
     * @param author
     * @param id
     */

    void updateBook(String title,String author, String publisher, int id);


    /**
     * Removes book by id
     * @param id
     */

    void removeBook(int id);



}
