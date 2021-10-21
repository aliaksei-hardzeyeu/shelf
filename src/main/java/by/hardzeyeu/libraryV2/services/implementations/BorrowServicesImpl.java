package by.hardzeyeu.libraryV2.services.implementations;

import by.hardzeyeu.libraryV2.connection.C3P0DataSource;
import by.hardzeyeu.libraryV2.dao.BookDAO;
import by.hardzeyeu.libraryV2.dao.BorrowDAO;
import by.hardzeyeu.libraryV2.models.Borrow;
import by.hardzeyeu.libraryV2.services.BorrowService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;


public class BorrowServicesImpl implements BorrowService {
    private static BorrowServicesImpl borrowServicesImpl;
    private BorrowDAO borrowDAO;


    private BorrowServicesImpl() {
        borrowDAO = new BorrowDAO();
    }

    public static BorrowServicesImpl getInstance() {
        if (borrowServicesImpl == null)
            borrowServicesImpl = new BorrowServicesImpl();
        return borrowServicesImpl;
    }

    public List<Borrow> getListOfBorrows() {

        return borrowDAO.getListOfBorrows();
    }


    public void addBorrow(int bookId, String userName, String userEmail, LocalDate borrowDate, Period timePeriod,
                          String status, String comment) {
        borrowDAO.addBorrow(bookId, userName, userEmail, borrowDate, timePeriod, status, comment);
    }

    public void updateBorrow(String status, int borrowId) {

        borrowDAO.updateBorrow(status, borrowId);
    }
}
