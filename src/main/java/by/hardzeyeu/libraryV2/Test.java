package by.hardzeyeu.libraryV2;

import java.time.LocalDate;
import java.time.Period;

public class Test {

    public static void main(String[] args) {
        int timePeriod = 15;
        Period p = Period.ofDays(timePeriod);
        LocalDate ld = LocalDate.of(2021, 10, 19);
        LocalDate ld2 = ld.plusDays(timePeriod);
        System.out.println(ld2);
    }
}
