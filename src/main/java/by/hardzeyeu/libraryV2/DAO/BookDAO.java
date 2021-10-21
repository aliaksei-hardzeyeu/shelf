package by.hardzeyeu.libraryV2.DAO;

import by.hardzeyeu.libraryV2.models.Book;

import java.util.List;


public interface BookDAO {

    /**
     * Gets particular book by isbn
     * @param isbn
     * @return book
     */

    Book getBook(String isbn);


    /**
     * Gets list of all books
     * @return arraylist of all books
     */

    List<Book> getListOfBooks();


    /**
     * Adds book to DB
     * @param title
     * @param publisher
     * @param page_count
     * @param isbn
     * @param desc
     * @param publDate
     * @param authors
     * @param genres
     * @param amount
     */

    void addBook(String title, String publisher, int page_count, String isbn, String desc, String publDate,
                 String authors, String genres, int amount);


    /**
     * Updates existing book
     * @param title
     * @param publisher
     * @param page_count
     * @param isbn
     * @param des
     * @param publDate
     * @param authors
     * @param genres
     * @param amount
     */

    void updateBook(String title, String publisher, int page_count, String isbn, String des, String publDate,
                    String authors, String genres, int amount, int book_id);


    /**
     * Removes particular book by id
     * @param book_id
     */

    void removeBook(int book_id);

}
