package by.hardzeyeu.libraryV2.DAO;

import java.time.LocalDate;
import java.time.Period;

public interface BorrowDAO {
//    userName;
//    String userEmail;
//    LocalDate borrowDate;
//    Period timePeriod;
//    String status;
//    String comment;

    void addBorrow(String userName, String userEmail, LocalDate borrowDate, Period timePeriod, String comment);

}
