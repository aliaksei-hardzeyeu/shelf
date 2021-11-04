package by.hardzeyeu.libraryV2.controllers;

import by.hardzeyeu.libraryV2.models.Book;
import by.hardzeyeu.libraryV2.services.BookService;
import by.hardzeyeu.libraryV2.services.implementations.BookServicesImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static by.hardzeyeu.libraryV2.dao.testData.BookDaoTestData.book1;
import static by.hardzeyeu.libraryV2.dao.testData.BookDaoTestData.books;


class BookServletTest extends Mockito {
    private static final String EMPTY_STRING = "";
    private static BookService bookService;
    private static HttpServletRequest request;
    private static HttpServletResponse response;
    private static RequestDispatcher requestDispatcher;
    private static BookServlet bookServlet;
    // Collection to store HttpServletRequest keys/values attributes
    private static final Map<String, Object> attributes = new HashMap<>();


    @BeforeAll
    public static void setup() throws SQLException, ServletException, IOException {
        //setup servlet methods
//        doNothing().when(bookServlet).viewMainPage(request, response);
//        doNothing().when(bookServlet).viewBook(request, response);
//        doNothing().when(bookServlet).updateBook(request, response);
//        doNothing().when(bookServlet).removeBook(request, response);
//        doNothing().when(bookServlet).addBook(request, response);

        //setup bookService
        bookService = mock(BookServicesImpl.class);
        when(bookService.getListOfBooks()).thenReturn(books);
        when(bookService.getBook(1)).thenReturn(book1);
        doNothing().when(bookService).addBook(isA(String.class), isA(String.class), isA(String.class));
        doNothing().when(bookService).updateBook(isA(String.class), isA(String.class), isA(String.class), isA(Integer.class));
        doNothing().when(bookService).removeBook(isA(Integer.class));

        //setup servlets objects
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        requestDispatcher = mock(RequestDispatcher.class);

        //setup controller
        bookServlet = new BookServlet();

        // Mock setAttribute
        Mockito.doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                String key = invocation.getArgumentAt(0, String.class);
                Object value = invocation.getArgumentAt(1, Object.class);
                attributes.put(key, value);
                return null;
            }
        }).when(request).setAttribute(Mockito.anyString(), Mockito.any());

        // Mock getAttribute
        Mockito.doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                String key = invocation.getArgumentAt(0, String.class);
                return attributes.get(key);
            }
        }).when(request).getAttribute(Mockito.anyString());
    }


    @AfterEach
    void resetAttributeStorage() {
        attributes.clear();
    }


    @Test
    void viewMainPage() throws ServletException, IOException {
        //reset only the number of invocations
        reset(requestDispatcher);
        //define mocks behavior for our case
        when(request.getParameter("action")).thenReturn("");
        when(request.getRequestDispatcher("WEB-INF/views/mainPage.jsp")).thenReturn(requestDispatcher);

        bookServlet.doGet(request, response);

        //check request attributes
        Assertions.assertEquals(books, request.getAttribute("books"));
        // verify called methods
        verify(requestDispatcher, times(1)).forward(request, response);

    }

    @Test
    void viewBook() {
    }

    @Test
    void updateBook() {
    }

    @Test
    void removeBook() {
    }

    @Test
    void addBook() {
    }
}