package by.hardzeyeu.libraryV2.controllers;

import by.hardzeyeu.libraryV2.models.Book;
import by.hardzeyeu.libraryV2.services.implementations.BookServicesImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.SQLException;
import java.util.List;

import static by.hardzeyeu.libraryV2.dao.testData.BookDaoTestData.book1;
import static by.hardzeyeu.libraryV2.dao.testData.BookDaoTestData.books;

public class BookServicesImplTest extends Mockito {
    private static BookServicesImpl bookServices;


    @BeforeAll
    public static void setup() throws SQLException {
        bookServices = mock(BookServicesImpl.class);
        when(bookServices.getBook(1)).thenReturn(book1);
        when(bookServices.getListOfBooks()).thenReturn(books);
        doNothing().when(bookServices).addBook(isA(String.class), isA(String.class), isA(String.class));
        doNothing().when(bookServices).updateBook(isA(String.class), isA(String.class), isA(String.class), isA(Integer.class));
        doNothing().when(bookServices).removeBook(isA(Integer.class));
    }


    @Test
    public void getBook_VerifySingleInvocation() throws SQLException {
        bookServices.getBook(1);
        verify(bookServices, times(1)).getBook(1);
    }

    @Test
    public void getListOfBooks_VerifySingleInvocation() {
        bookServices.getListOfBooks();
        verify(bookServices, times(1)).getListOfBooks();
    }

    @Test
    public void addBook_VerifySingleInvocation() {
        bookServices.addBook("t1", "p1", "a1");
        verify(bookServices, times(1)).addBook("t1", "p1", "a1");
    }

    @Test
    public void updateBook_VerifySingleInvocation() {
        bookServices.updateBook("t1", "p1", "a1", 666);
        verify(bookServices, times(1)).updateBook("t1", "p1", "a1", 666);
    }

    @Test
    public void removeBook() {
        bookServices.removeBook( 666);
        verify(bookServices, times(1)).removeBook(666);
    }
}
