package by.hardzeyeu.libraryV2.controllers;

import by.hardzeyeu.libraryV2.models.Book;
import by.hardzeyeu.libraryV2.models.Borrow;
import by.hardzeyeu.libraryV2.services.BookService;
import by.hardzeyeu.libraryV2.services.BorrowService;
import by.hardzeyeu.libraryV2.services.implementations.BookServicesImpl;
import by.hardzeyeu.libraryV2.services.implementations.BorrowServicesImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "BookServlet", value = "/")
public class BookServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println("doGET ACTION = " + action);
        System.out.println("context bookId = " + getServletContext().getAttribute("bookId"));

       if (action == null && (getServletContext().getAttribute("bookId") == null)) {
           System.out.println("in 1 case");
           viewMainPage(request, response);

       } else if (action.equals("update") || action.equals("add")) {
           System.out.println("in 2 case");
          viewMainPage(request, response);


       } else {
           System.out.println("in 3 case");
           request.setAttribute("bookId", getServletContext().getAttribute("bookId"));
           viewBookPage(request, response);

       }
    }

    void viewMainPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookServicesImpl bookServicesImpl = BookServicesImpl.getInstance();

        request.setAttribute("listOfBooks", bookServicesImpl.getListOfBooks());

        request.getRequestDispatcher("WEB-INF/views/mainPage.jsp").forward(request, response);
    }

    void viewBookPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int bookId;

        if (request.getParameter("bookId") == null) {
            bookId = (Integer) getServletContext().getAttribute("bookId");
            System.out.println("bookId from context = " + bookId);
        } else {
            bookId = Integer.parseInt(request.getParameter("bookId"));
            System.out.println("BookId from request = " + bookId);
        }

        System.out.println("doGET viewBookPage 1");

        BookService bookServicesImpl = BookServicesImpl.getInstance();
        BorrowService borrowService = BorrowServicesImpl.getInstance();
        System.out.println("doGET viewBookPage 2");


        Book book = bookServicesImpl.getBook(bookId);
        System.out.println("doGET viewBookPage 3");

        List<Borrow> listOfBorrows = borrowService.getListOfBorrows(bookId);

        request.setAttribute("book", book);
        request.setAttribute("listOfBorrows", listOfBorrows);
        request.setAttribute("actionOnPage", "update");
        System.out.println("doGET viewBookPage 4");

        request.getRequestDispatcher("WEB-INF/views/bookPage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println("doPOST ACTION= " + action);

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
        System.out.println("START OF view METHOD");

        int bookId = Integer.parseInt(request.getParameter("bookId"));
        System.out.println("1 OF view METHOD");

        BookService bookServicesImpl = BookServicesImpl.getInstance();
        BorrowService borrowService = BorrowServicesImpl.getInstance();
        System.out.println("2 OF view METHOD");


        Book book = bookServicesImpl.getBook(bookId);
        System.out.println("3 OF view METHOD");

        List<Borrow> listOfBorrows = borrowService.getListOfBorrows(bookId);

        request.setAttribute("book", book);
        request.setAttribute("listOfBorrows", listOfBorrows);
        request.setAttribute("actionOnPage", "update");
        System.out.println("end OF view METHOD");

        request.getRequestDispatcher("WEB-INF/views/bookPage.jsp").forward(request, response);

    }

    void updateBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("START OF UPDATE METHOD");
        BookServicesImpl bookServicesImpl = BookServicesImpl.getInstance();

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

        bookServicesImpl.updateBook(title, publisher, pageCount, isbn, des, publDate, authors, genres, amount, bookId);
        System.out.println("END OF UPDATE METHOD");
        doGet(request, response);
    }

    void removeBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookServicesImpl bookServicesImpl = BookServicesImpl.getInstance();

        bookServicesImpl.removeBook(Integer.parseInt(request.getParameter("bookId")));
        doGet(request, response);
    }

    void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookServicesImpl bookServicesImpl = BookServicesImpl.getInstance();
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

        bookServicesImpl.addBook(title, publisher, pageCount, isbn, des, publDate, authors, genres, amount);
        System.out.println("end OF ADD METHOD");

        doGet(request, response);
    }


}