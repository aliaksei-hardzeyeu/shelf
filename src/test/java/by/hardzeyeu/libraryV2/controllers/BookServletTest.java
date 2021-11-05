package by.hardzeyeu.libraryV2.controllers;

import by.hardzeyeu.libraryV2.dao.BookDao;
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
import static org.mockito.Mockito.clearInvocations;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class BookServletTest {
    // Collection to store HttpServletRequest keys/values attributes
    private static final Map<String, Object> attributes = new HashMap<>();
    private static BookService bookServicesImpl;
    private static HttpServletRequest request;
    private static HttpServletResponse response;
    private static RequestDispatcher requestDispatcher;
    private static BookServlet bookServlet;
    private static BookDao bookDao;

    @BeforeAll
    public static void setup() throws SQLException, ServletException, IOException {
        //setup bookService
        bookDao = mock(BookDao.class);
        when(bookDao.getListOfBooks()).thenReturn(books);
        when(bookDao.getBook(1)).thenReturn(book1);
        when(bookDao.updateBook(1, "title10", "author10", "publisher10")).thenReturn(true);
        when(bookDao.addBook("title11", "author11", "publisher11")).thenReturn(true);
        when(bookDao.removeBook(1)).thenReturn(true);

        //setup servlets objects
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        requestDispatcher = mock(RequestDispatcher.class);

        //setup controller
        bookServicesImpl = new BookServicesImpl(bookDao);
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
        clearInvocations(requestDispatcher);
        clearInvocations(response);
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
    void doGet_Action_Add() throws ServletException, IOException {
        //define mocks behavior for our case
        when(request.getParameter("action")).thenReturn("addNew");
        when(request.getRequestDispatcher("WEB-INF/views/addNew.jsp")).thenReturn(requestDispatcher);

        bookServlet.doGet(request, response);

        // verify called methods
        verify(requestDispatcher, times(1)).forward(request, response);
    }


    @Test
    void doGet_Action_Update() throws ServletException, IOException {
        //define mocks behavior for our case
        when(request.getParameter("action")).thenReturn("update");
        when(request.getParameter("id")).thenReturn("1");
        when(request.getRequestDispatcher("WEB-INF/views/bookPage.jsp")).thenReturn(requestDispatcher);

        bookServlet.doGet(request, response);

        //check request attributes
        Assertions.assertEquals(book1, request.getAttribute("book"));
        // verify called methods
        verify(requestDispatcher, times(1)).forward(request, response);
    }


    @Test
    void doPost_Action_Update() throws ServletException, IOException {
        //define mocks behavior for our case
        when(request.getParameter("action")).thenReturn("update");
        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("title")).thenReturn("title10");
        when(request.getParameter("author")).thenReturn("author10");
        when(request.getParameter("publisher")).thenReturn("publisher10");

        bookServlet.doPost(request, response);

        // verify called methods
        verify(response, times(1)).sendRedirect("/");
    }


    @Test
    void doPost_Action_Remove() throws ServletException, IOException {
        //define mocks behavior for our case
        when(request.getParameter("action")).thenReturn("remove");
        when(request.getParameter("id")).thenReturn("1");

        bookServlet.doPost(request, response);

        // verify called methods
        verify(response, times(1)).sendRedirect("/");
    }


    @Test
    void doPost_Action_Add() throws ServletException, IOException {
        //define mocks behavior for our case
        when(request.getParameter("action")).thenReturn("add");
        when(request.getParameter("title")).thenReturn("title10");
        when(request.getParameter("author")).thenReturn("author10");
        when(request.getParameter("publisher")).thenReturn("publisher10");

        bookServlet.doPost(request, response);

        // verify called methods
        verify(response, times(1)).sendRedirect("/");
    }
}