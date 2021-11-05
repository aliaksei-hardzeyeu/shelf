package by.hardzeyeu.libraryV2.controllers;

import by.hardzeyeu.libraryV2.models.Book;
import by.hardzeyeu.libraryV2.services.BookService;
import by.hardzeyeu.libraryV2.services.implementations.BookServicesImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.Spy;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.clearInvocations;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class BookServletTest {
    private static final String EMPTY_STRING = "";
    private static BookService bookServicesImpl;
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
        bookServicesImpl = mock(BookServicesImpl.class);
        when(bookServicesImpl.getListOfBooks()).thenReturn(books);
        when(bookServicesImpl.getBook(1)).thenReturn(book1);
        doNothing().when(bookServicesImpl).addBook(isA(String.class), isA(String.class), isA(String.class));
        doNothing().when(bookServicesImpl).updateBook(isA(String.class), isA(String.class), isA(String.class), isA(Integer.class));
        doNothing().when(bookServicesImpl).removeBook(isA(Integer.class));

        //setup servlets objects
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        requestDispatcher = mock(RequestDispatcher.class);

        //setup controller
        bookServlet = new BookServlet(bookServicesImpl);

        // Mock setAttribute
        Mockito.doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                String key = invocation.getArgument(0, String.class);
                Object value = invocation.getArgument(1, Object.class);
                attributes.put(key, value);
                return null;
            }
        }).when(request).setAttribute(Mockito.anyString(), Mockito.any());

        // Mock getAttribute
        Mockito.doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                String key = invocation.getArgument(0, String.class);
                return attributes.get(key);
            }
        }).when(request).getAttribute(Mockito.anyString());
    }


    @AfterEach
    void resetAttributeStorage() {
        attributes.clear();
    }


    @Test
    void doGet_Action_ViewMainPage() throws ServletException, IOException {
        //reset only the number of invocations
        clearInvocations(requestDispatcher);
        //define mocks behavior for our case
        when(request.getParameter("action")).thenReturn("mainPage");
        when(request.getRequestDispatcher("WEB-INF/views/mainPage.jsp")).thenReturn(requestDispatcher);

        bookServlet.doGet(request, response);

        //check request attributes
        Assertions.assertEquals(books, request.getAttribute("listOfBooks"));
        // verify called methods
        verify(requestDispatcher, times(1)).forward(request, response);

    }

    @Test
    void doGet_Action_ViewBook_Type_Add() throws ServletException, IOException {
//reset only the number of invocations
        clearInvocations(requestDispatcher);
        //define mocks behavior for our case
        when(request.getParameter("action")).thenReturn("view");
        when(request.getParameter("type")).thenReturn("new");
        when(request.getRequestDispatcher("WEB-INF/views/bookPage.jsp")).thenReturn(requestDispatcher);

        bookServlet.doGet(request, response);

        //check request attributes
        Assertions.assertEquals("add", request.getAttribute("actionOnPage"));
        // verify called methods
        verify(requestDispatcher, times(1)).forward(request, response);

    }

    @Test
    void doGet_Action_ViewBook_Type_Existing() throws ServletException, IOException {
//reset only the number of invocations
        clearInvocations(requestDispatcher);
        //define mocks behavior for our case
        when(request.getParameter("action")).thenReturn("view");
        when(request.getParameter("type")).thenReturn("existing");
        when(request.getParameter("id")).thenReturn("1");
        when(request.getRequestDispatcher("WEB-INF/views/bookPage.jsp")).thenReturn(requestDispatcher);

        bookServlet.doGet(request, response);

        //check request attributes
        Assertions.assertEquals("update", request.getAttribute("actionOnPage"));
        Assertions.assertEquals(book1, request.getAttribute("book"));
        // verify called methods
        verify(requestDispatcher, times(1)).forward(request, response);

    }

    @Test
    void doPost_Action_UpdateBook() throws ServletException, IOException {
        clearInvocations(requestDispatcher);
        //define mocks behavior for our case
        BookServlet spyBookServlet = Mockito.spy(bookServlet);

        when(request.getParameter("action")).thenReturn("update");
        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("title")).thenReturn("title1");
        when(request.getParameter("author")).thenReturn("author1");
        when(request.getParameter("publisher")).thenReturn("publisher1");
        doNothing().when(spyBookServlet).viewMainPage(request, response);

        spyBookServlet.doPost(request, response);

        // verify called methods
        verify(bookServicesImpl, times(1)).updateBook(isA(String.class), isA(String.class), isA(String.class), isA(Integer.class));
        verify(spyBookServlet, times(1)).viewMainPage(request, response);
    }

    @Test
    void doPost_Action_RemoveBook() throws ServletException, IOException {
        clearInvocations(response);

        BookServlet spyBookServlet = Mockito.spy(bookServlet);

        when(request.getParameter("action")).thenReturn("remove");
        when(request.getParameter("id")).thenReturn(String.valueOf(1));
        doNothing().when(spyBookServlet).viewMainPage(request, response);

        spyBookServlet.doPost(request, response);


        verify(bookServicesImpl, times(1)).removeBook(isA(Integer.class));
        verify(spyBookServlet, times(1)).viewMainPage(request, response);
    }

    @Test
    void doPost_Action_AddBook() throws ServletException, IOException {
        clearInvocations(response);
        //define mocks behavior for our case
        BookServlet spyBookServlet = Mockito.spy(bookServlet);

        when(request.getParameter("action")).thenReturn("add");
        when(request.getParameter("title")).thenReturn("title1");
        when(request.getParameter("author")).thenReturn("author1");
        when(request.getParameter("publisher")).thenReturn("publisher1");

        spyBookServlet.doPost(request, response);

        // verify called methods
        verify(bookServicesImpl, times(1)).addBook(isA(String.class), isA(String.class), isA(String.class));
        verify(response, times(1)).sendRedirect("/");
    }
}