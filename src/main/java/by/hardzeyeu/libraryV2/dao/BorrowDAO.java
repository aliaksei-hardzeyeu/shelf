package by.hardzeyeu.libraryV2.dao;

import by.hardzeyeu.libraryV2.connection.C3P0DataSource;
import by.hardzeyeu.libraryV2.models.Borrow;
import by.hardzeyeu.libraryV2.dto.StatusWorker;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static by.hardzeyeu.libraryV2.services.Utils.convertToLocalDateViaSqlDate;

public class BorrowDAO {


    public List<Borrow> getListOfBorrows(int bookId) {
        List<Borrow> listOfBorrows = new ArrayList<>();
        String query = "SELECT * FROM borrows WHERE book_id = ?";

        try (Connection connection = C3P0DataSource.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, bookId);

            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Borrow borrow = new Borrow();

                borrow.setBookId(result.getInt("book_id"));
                borrow.setUserName(result.getString("user_name"));
                borrow.setUserEmail(result.getString("user_email"));
                borrow.setBorrowDate(convertToLocalDateViaSqlDate(result.getDate("borrow_date")));
                borrow.setTimePeriod(result.getInt("time_period"));
                borrow.setComment(result.getString("comment"));
                borrow.setBorrowId(result.getInt("borrow_id"));


                listOfBorrows.add(borrow);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listOfBorrows;
    }


    private void writeParamsToBorrow(ResultSet result, Borrow borrow) throws SQLException {
        borrow.setBookId(result.getInt("book_id"));
        borrow.setUserName(result.getString("user_name"));
        borrow.setUserEmail(result.getString("user_email"));
        borrow.setBorrowDate(convertToLocalDateViaSqlDate(result.getDate("date")));
        borrow.setStatus(result.getString("status"));
        borrow.setBorrowId(result.getInt("borrow_id"));
    }


    public void addBorrow(int bookId, String userName, String userEmail, Date borrowDate, int timePeriod, String comment) {

        String query = "INSERT INTO borrows (book_id, user_name, user_email, borrow_date, time_period," +
                " comment) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = C3P0DataSource.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, bookId);
            preparedStatement.setString(2, userName);
            preparedStatement.setString(3, userEmail);
            preparedStatement.setDate(4, borrowDate);
            preparedStatement.setInt(5, timePeriod);
            preparedStatement.setString(6, comment);

            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateBorrow(String status, int borrowId) {

        String query = "UPDATE borrows SET status = ? WHERE borrow_id = ?";

        try (Connection connection = C3P0DataSource.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, borrowId);

            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public StatusWorker getDataForStatusWorker() {
        String query =
         "SELECT COUNT(IF(status='damaged', 1, null)) 'damaged', " +
                 "COUNT(IF(status ='lost', 1, null)) 'lost', " +
                 "COUNT(IF(status ='returned', 1, null)) 'returned', " +
                 "COUNT(IF(status = NULL, 1, null)) 'borrowed' FROM borrows;";

        StatusWorker statusWorker = new StatusWorker();

        try (Connection connection = C3P0DataSource.getInstance().getConnection()) {
            Statement statement = connection.createStatement();

            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                statusWorker.setDamaged(result.getInt("damaged"));
                statusWorker.setLost(result.getInt("lost"));
                statusWorker.setReturned(result.getInt("returned"));
                statusWorker.setBorrowed(result.getInt("borrowed"));
            };


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return statusWorker;
    }
}
