package by.hardzeyeu.libraryV2.services.implementations;

import by.hardzeyeu.libraryV2.dao.BorrowDAO;
import by.hardzeyeu.libraryV2.dto.StatusWorker;
import by.hardzeyeu.libraryV2.models.Book;
import by.hardzeyeu.libraryV2.models.Borrow;
import by.hardzeyeu.libraryV2.services.BorrowService;
import by.hardzeyeu.libraryV2.services.Utils;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
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


    public void addBorrow(int bookId, String userName, String userEmail, int timePeriod, String comment) {
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

    /**
     * Counts due date -> borrow date + period
     * @param borrow
     * @return LocalDate due date
     */

    public LocalDate countDueDate(Borrow borrow) {
        return borrow.getBorrowDate().plus(Period.ofMonths(borrow.getTimePeriod()));
    }

    public LocalDate countReturnDate(Borrow borrow) {
        return borrow.getChangedStatusDate();
    }


    public void setStatus(Book book) {
        StatusWorker statusWorker = borrowDAO.getDataForStatusWorker(book);

        int returned = statusWorker.getReturned();
        int damaged = statusWorker.getDamaged();
        int lost = statusWorker.getLost();
        int borrowed = statusWorker.getLost();
        int totalAmount = book.getAmount();


        int available = totalAmount - damaged - lost - borrowed;

        if (available > 0) {
            book.setStatus("available " + available + " of " + totalAmount);
        }

    }
}
