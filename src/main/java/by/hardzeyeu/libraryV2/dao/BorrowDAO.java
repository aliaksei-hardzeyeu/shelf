package by.hardzeyeu.libraryV2.dao;

import by.hardzeyeu.libraryV2.connection.C3P0DataSource;
import by.hardzeyeu.libraryV2.models.Book;
import by.hardzeyeu.libraryV2.models.Borrow;
import by.hardzeyeu.libraryV2.dto.StatusWorker;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
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

    /**
     * Retrieves number of damaged, lost, returned, borrowed instances of book. And puts in borrowDates
     *
     *
     * @param book
     * @return
     */
    //todo ПОДУМАТЬ МОЖ НЕ НУЖНА ХЭШМАПА, А ПРОСТО ЛИСТ С ДАТАМИ, РАЗДЕЛИТЬ НАВЕРНОЕ ВСЁ ЭТО НА МЕТОДЫ
    //todo ОСТАВИТЬ ЗДЕСЬ ТОЛЬКО ДАО, ВСЁ ОСТАЛЬНОЕ В СЕРВИСЫ
    public StatusWorker getDataForStatusWorker(Book book) {
        String query1 =
         "SELECT COUNT(IF(status='damaged', 1, null)) 'damaged', " +
                 "COUNT(IF(status ='lost', 1, null)) 'lost', " +
                 "COUNT(IF(status ='returned', 1, null)) 'returned', " +
                 "COUNT(IF(status = NULL, 1, null)) 'borrowed' FROM borrows WHERE book_id = ?;";

        String query2 = "SELECT borrow_date, time_period FROM borrows WHERE status = NULL";

        StatusWorker statusWorker = new StatusWorker();
        HashMap<Integer, LocalDate> borrowDates = new HashMap<>();


        try (Connection connection = C3P0DataSource.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query1);

            preparedStatement.setInt(1, book.getBookId());

            ResultSet result1 = preparedStatement.executeQuery();

            while (result1.next()) {
                statusWorker.setDamaged(result1.getInt("damaged"));
                statusWorker.setLost(result1.getInt("lost"));
                statusWorker.setReturned(result1.getInt("returned"));
                statusWorker.setBorrowed(result1.getInt("borrowed"));
            }

            Statement statement = connection.createStatement();


            ResultSet result2 = statement.executeQuery(query2);

            while (result2.next()) {
                statusWorker.setDamaged(result2.getInt("damaged"));
                statusWorker.setLost(result2.getInt("lost"));
                statusWorker.setReturned(result2.getInt("returned"));
                statusWorker.setBorrowed(result2.getInt("borrowed"));
            }



        } catch (SQLException e) {
            e.printStackTrace();
        }

        return statusWorker;
    }
}
