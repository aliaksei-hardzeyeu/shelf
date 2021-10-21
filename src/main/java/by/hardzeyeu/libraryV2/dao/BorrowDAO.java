package by.hardzeyeu.libraryV2.dao;

import by.hardzeyeu.libraryV2.connection.C3P0DataSource;
import by.hardzeyeu.libraryV2.models.Borrow;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import static by.hardzeyeu.libraryV2.services.Utils.convertToLocalDateViaSqlDate;

public class BorrowDAO {


    public List<Borrow> getListOfBorrows() {
        List<Borrow> listOfBorrows = new ArrayList<>();
        String query = "SELECT * FROM borrows";

        try (Connection connection = C3P0DataSource.getInstance().getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                Borrow borrow = new Borrow();

                writeParamsToBorrow(result, borrow);

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
        borrow.setTimePeriod(result.getDate("isbn")); //todo UTIL -> date to time period
        borrow.setStatus(result.getString("status"));
        borrow.setBorrowId(result.getInt("borrow_id"));
    }


    public void addBorrow(int bookId, String userName, String userEmail, LocalDate borrowDate, Period timePeriod,
                          String status, String comment) {

        String query = "INSERT INTO borrows (book_id, user_name, user_email, borrow_date, time_period," +
                " status, comment) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = C3P0DataSource.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, bookId);
            preparedStatement.setString(2, userName);
            preparedStatement.setString(3, userEmail);
            preparedStatement.setDate(4, borrowDate);//todo разобраться с датами и временем
            preparedStatement.setString(5, timePeriod);//todo разобраться с датами и временем
            preparedStatement.setString(6, status);
            preparedStatement.setString(7, comment);

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
}
