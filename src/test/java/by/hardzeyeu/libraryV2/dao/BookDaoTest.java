package by.hardzeyeu.libraryV2.dao;

import by.hardzeyeu.libraryV2.dao.testData.BookDaoTestData;
import by.hardzeyeu.libraryV2.models.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


@Testcontainers
class BookDaoTest {
    private static String MYSQL_LATEST_IMAGE = "mysql:latest";
    private static Connection connection;
    private static BookDao bookDAO;


    @Container
    private static JdbcDatabaseContainer mysql = new MySQLContainer<>(MYSQL_LATEST_IMAGE)
            .withInitScript("db/initDB.sql");


    @BeforeAll
    public static void setup() throws SQLException {
        connection = DriverManager.getConnection(
                mysql.getJdbcUrl(),
                mysql.getUsername(),
                mysql.getPassword()
        );
        bookDAO = new BookDao(connection);
    }


    @BeforeEach
    public void refreshData() throws SQLException {
        Statement statement = connection.createStatement();
        statement.addBatch("DELETE FROM shelf;");
        statement.addBatch("DELETE FROM publishers;");
        statement.addBatch("ALTER TABLE shelf AUTO_INCREMENT = 1;");
        statement.addBatch("INSERT INTO shelf (title, author) VALUES ('title1', 'author1'), ('title2', 'author2'), ('title3', 'author3');");
        statement.addBatch("INSERT INTO publishers (id, publisher) VALUES (1, 'publisher1'), (2, 'publisher2'), (3, 'publisher3');");
        statement.executeBatch();
    }

    @Test
    public void getListOfBooks_ReturnsAllExistingBooksAsList() throws SQLException {
        List<Book> listOfBooks = bookDAO.getListOfBooks();
        Assertions.assertEquals(BookDaoTestData.books, listOfBooks);
    }


    @Test
    public void getBook_ByExistingId_ReturnsBook() throws SQLException {
        Book newBook = bookDAO.getBook(2);
        Assertions.assertEquals(BookDaoTestData.book2, newBook);
    }


    @Test
    public void getBook_ByNotExistingId_ThrowsSqlException() {
        Assertions.assertThrows(SQLException.class, () -> bookDAO.getBook(5));
    }


    @Test
    public void getBookId_ByExistingTitle_ReturnsId() {
        int testId = BookDaoTestData.book1.getId();
        Assertions.assertEquals(bookDAO.getBookId("title1"), testId);
    }


    @Test
    public void addBook_ReturnAddedBook() throws SQLException {
        Book newBook = new Book(4, "title666", "author666", "publisher666");
        bookDAO.addBook("title666", "author666", "publisher666");

        Assertions.assertEquals(newBook, bookDAO.getBook(4));
    }


    @Test
    public void updateBook_ReturnUpdatedBook() throws SQLException {
        Book updatedBook = new Book(1, "title777", "author777", "publisher777");
        bookDAO.updateBook(1, "title777", "author777", "publisher777");

        Assertions.assertEquals(updatedBook, bookDAO.getBook(1));
    }


    @Test
    public void removeBook_ReturnListWithoutRemovedBook() throws SQLException {
        List<Book> list1 = bookDAO.getListOfBooks();
        Book book = new Book(1, "title1", "author1", "publisher1");
        Assertions.assertEquals(book, list1.get(0));

        bookDAO.removeBook(1);
        List<Book> list2 = bookDAO.getListOfBooks();

        Assertions.assertEquals(2, list2.size());
        Assertions.assertThrows(SQLException.class, () -> bookDAO.getBook(1));
    }
}