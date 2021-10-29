package by.hardzeyeu.libraryV2.dto;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public class StatusWorker {
    int returned;
    int damaged;
    int lost;
    int borrowed;

    HashMap<Integer, LocalDate> borrowDates;

    public HashMap<Integer, LocalDate> getBorrowDates() {
        return borrowDates;
    }

    public void setBorrowDates(HashMap<Integer, LocalDate> borrowDates) {
        this.borrowDates = borrowDates;
    }

    public int getReturned() {
        return returned;
    }

    public void setReturned(int returned) {
        this.returned = returned;
    }

    public int getDamaged() {
        return damaged;
    }

    public void setDamaged(int damaged) {
        this.damaged = damaged;
    }

    public int getLost() {
        return lost;
    }

    public void setLost(int lost) {
        this.lost = lost;
    }

    public int getBorrowed() {
        return borrowed;
    }

    public void setBorrowed(int borrowed) {
        this.borrowed = borrowed;
    }

    @Override
    public String toString() {
        return "StatusWorker{" +
                "returned=" + returned +
                ", damaged=" + damaged +
                ", lost=" + lost +
                ", borrowed=" + borrowed +
                '}';
    }
}
