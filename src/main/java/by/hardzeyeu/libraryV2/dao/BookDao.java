package by.hardzeyeu.libraryV2.dao;

import by.hardzeyeu.libraryV2.connection.DBWorker;
import by.hardzeyeu.libraryV2.models.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
    static DBWorker dbWorker = new DBWorker();
    Connection connection;


    public BookDao() {
        this.connection = dbWorker.getConnection();
    }

    public BookDao(Connection connection) {
        this.connection = connection;
    }

    public Book getBook(int id) throws SQLException {
        Book book = new Book();
        String query1 = "SELECT shelf.title, shelf.author, publishers.publisher FROM shelf LEFT JOIN publishers ON shelf.id = publishers.id WHERE shelf.id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query1);

        preparedStatement.setInt(1, id);


        ResultSet result = preparedStatement.executeQuery();

        result.next();

        book.setId(id);
        book.setTitle(result.getString("title"));
        book.setAuthor(result.getString("author"));
        book.setPublisher(result.getString("publisher"));

        return book;
    }

    public List<Book> getListOfBooks() throws SQLException {
        List<Book> listOfBooks = new ArrayList<>();
        String query = "SELECT shelf.id, shelf.title, shelf.author, publishers.publisher FROM shelf LEFT JOIN publishers ON shelf.id = publishers.id";

        if (connection.isClosed()) System.out.println("connection closed");

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(query);

        while (result.next()) {
            Book book = new Book();

            book.setId(result.getInt("id"));
            book.setTitle(result.getString("title"));
            book.setAuthor(result.getString("author"));
            book.setPublisher(result.getString("publisher"));

            listOfBooks.add(book);
        }
        return listOfBooks;
    }


    public int getBookId(String title) {
        int id = 0;
        String query = "SELECT id FROM shelf WHERE title = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, title);

            ResultSet result = preparedStatement.executeQuery();

            result.next();
            id = result.getInt(1);


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public boolean addBook(String title, String author, String publisher) {

        String query1 = "INSERT INTO shelf (title, author) VALUES (?, ?)";
        String query2 = "INSERT INTO publishers (id, publisher) VALUES (?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query1);

            preparedStatement.setString(1, title);
            preparedStatement.setString(2, author);

            preparedStatement.execute();

            preparedStatement = connection.prepareStatement(query2);
            preparedStatement.setInt(1, getBookId(title));
            preparedStatement.setString(2, publisher);

            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean updateBook(int id, String title, String author, String publisher) {

        String query1 = "UPDATE shelf SET title = ?, author = ? WHERE id = ?";
        String query2 = "UPDATE publishers SET publisher = ? WHERE id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query1);

            preparedStatement.setString(1, title);
            preparedStatement.setString(2, author);
            preparedStatement.setInt(3, id);

            preparedStatement.execute();

            preparedStatement = connection.prepareStatement(query2);
            preparedStatement.setString(1, publisher);
            preparedStatement.setInt(2, id);

            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return true;
    }

    public boolean removeBook(int id) {
        String query1 = "DELETE FROM shelf WHERE id = ?";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query1);

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}