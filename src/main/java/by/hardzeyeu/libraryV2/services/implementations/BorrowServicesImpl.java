package by.hardzeyeu.libraryV2.services.implementations;

import by.hardzeyeu.libraryV2.connection.C3P0DataSource;
import by.hardzeyeu.libraryV2.dao.BookDAO;
import by.hardzeyeu.libraryV2.dao.BorrowDAO;
import by.hardzeyeu.libraryV2.models.Borrow;
import by.hardzeyeu.libraryV2.services.BorrowService;
import by.hardzeyeu.libraryV2.services.Utils;

import java.sql.Connection;
import java.sql.Date;
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


    public void addBorrow(int bookId, String userName, String userEmail, LocalDate borrowDate, int timePeriod, String comment) {
        Date borrowDateSql = Utils.convertToSqlDateFromLocalDate(LocalDate.now());
        borrowDAO.addBorrow(bookId, userName, userEmail, borrowDateSql, timePeriod, comment);
    }

    public void updateBorrow(String status, int borrowId) {

        borrowDAO.updateBorrow(status, borrowId);
    }

    public List<Borrow> getListOfBorrows(int bookId) {
        List<Borrow> listOfBorrows = borrowDAO.getListOfBorrows(bookId);

        for (Borrow borrow: listOfBorrows) {
            borrow.setDueDate(countDueDate(borrow));
        }

        return listOfBorrows;
    }

    public LocalDate countDueDate(Borrow borrow) {
        return borrow.getBorrowDate().plus(Period.ofMonths(borrow.getTimePeriod()));
    }

    public LocalDate countReturnDate(Borrow borrow) {
        return borrow.getChangedStatusDate();
    }
}
