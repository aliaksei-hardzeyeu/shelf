package by.hardzeyeu.libraryV2.services;

import by.hardzeyeu.libraryV2.models.Borrow;

import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public interface BorrowService {

    List<Borrow> getListOfBorrows(int bookId);


    void addBorrow(int bookId, String userName, String userEmail, int timePeriod, String comment);


    void updateBorrow(String status, int borrowId);

}
