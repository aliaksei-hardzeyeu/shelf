package by.hardzeyeu.libraryV2.controllers;

import by.hardzeyeu.libraryV2.models.Book;
import by.hardzeyeu.libraryV2.services.BookServices;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "BookServlet", value = "/")
public class BookServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookServices bookServices = BookServices.getInstance();
        request.setAttribute("listOfBooks", bookServices.getListOfBooks());
        request.getRequestDispatcher("WEB-INF/views/mainPage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println("DOPOST ACTION= " + action);

        switch (action) {

            case "view":
                viewBook(request, response);
                break;

            case "update":
                updateBook(request, response);
                break;

            case "remove":
                removeBook(request, response);
                break;

            case "add":
                addBook(request, response);
                break;

            default:
                throw new ServletException("Invalid action parameter");
        }

    }

    void viewBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookServices bookServices = BookServices.getInstance();
        Book book = bookServices.getBook(Integer.parseInt(request.getParameter("bookId")));
        request.setAttribute("book", book);
        request.setAttribute("actionOnPage", "update");
        request.getRequestDispatcher("WEB-INF/views/bookPage.jsp").forward(request, response);

    }

    void updateBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("START OF UPDATE METHOD");
        BookServices bookServices = BookServices.getInstance();

        String title = request.getParameter("title");
        String publisher = request.getParameter("publisher");
        int pageCount = Integer.parseInt(request.getParameter("pageCount"));
        String isbn = request.getParameter("isbn");
        String des = request.getParameter("description");
        String publDate = request.getParameter("publDate");
        String authors = request.getParameter("authors");
        String genres = request.getParameter("genres");
        int amount = Integer.parseInt(request.getParameter("amount"));
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        System.out.println("MIDDLE OF UPDATE METHOD");

        bookServices.updateBook(title, publisher, pageCount, isbn, des, publDate, authors, genres, amount, bookId);
        System.out.println("END OF UPDATE METHOD");
        doGet(request, response);
    }

    void removeBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookServices bookServices = BookServices.getInstance();

        bookServices.removeBook(Integer.parseInt(request.getParameter("bookId")));
        doGet(request, response);
    }

    void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookServices bookServices = BookServices.getInstance();
        System.out.println("START OF ADD METHOD");

        if (request.getParameter("title") == null) {
            request.setAttribute("actionOnPage", "add");
            request.getRequestDispatcher("WEB-INF/views/bookPage.jsp").forward(request, response);
        }

        String title = request.getParameter("title");
        String publisher = request.getParameter("publisher");
        int pageCount = Integer.parseInt(request.getParameter("pageCount"));
        String isbn = request.getParameter("isbn");
        String des = request.getParameter("description");
        String publDate = request.getParameter("publDate");
        String authors = request.getParameter("authors");
        String genres = request.getParameter("genres");
        int amount = Integer.parseInt(request.getParameter("amount"));
        System.out.println("middle OF ADD METHOD");

        bookServices.addBook(title, publisher, pageCount, isbn, des, publDate, authors, genres, amount);
        System.out.println("end OF ADD METHOD");

        doGet(request, response);
    }
}