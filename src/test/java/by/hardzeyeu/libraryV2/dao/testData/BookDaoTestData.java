package by.hardzeyeu.libraryV2.dao.testData;

import by.hardzeyeu.libraryV2.models.Book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookDaoTestData {
    public static final Integer  COMPANY_1_ID = 1;
    public static final String COMPANY_2_ID = "2";
    public static final String COMPANY_3_ID = "3";
    public static final Integer  NOT_FOUND_ID = 50;
    public static final Book book1 = new Book(1, "title1", "author1", "publisher1");
    public static final Book book2 = new Book(2, "title2", "author2", "publisher2");
    public static final Book book3 = new Book(3, "title3", "author3", "publisher3");
    public static final List<Book> books = List.of(book1, book2, book3);


    public static Book getNew() {
        return new Book(4,"title4", "author4", "publisher4");
    }

    public static Book getUpdated() {
        return new Book(1,"title666", "author666", "publisher666");
    }
}
