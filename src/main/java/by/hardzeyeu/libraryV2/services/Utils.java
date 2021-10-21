package by.hardzeyeu.libraryV2.services;

import java.sql.Date;
import java.time.LocalDate;

public class Utils {
    /**
     * Converts To LocalDate from SqlDate
     * @param dateToConvert
     * @return LocalDate
     */
    public static LocalDate convertToLocalDateViaSqlDate(Date dateToConvert) {
        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
    }
}
