package by.hardzeyeu.libraryV2.services;

import by.hardzeyeu.libraryV2.models.Borrow;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public interface BorrowService {

    List<Borrow> getListOfBorrows();


    void addBorrow(int bookId, String userName, String userEmail, LocalDate borrowDate, Period timePeriod,
                   String status, String comment);


    void updateBorrow(String status, int borrowId);

}
