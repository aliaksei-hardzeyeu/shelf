package by.hardzeyeu.libraryV2.controllers;

import by.hardzeyeu.libraryV2.models.Book;
import by.hardzeyeu.libraryV2.services.BookService;
import by.hardzeyeu.libraryV2.services.implementations.BookServicesImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "BookServlet", value = "/")
public class BookServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println("doPOST ACTION= " + action);

        switch (action == null ? "mainPage" : action) {

            case "update":
                updateBook(request, response);
                break;

            case "remove":
                removeBook(request, response);
                break;

            case "add":
                addBook(request, response);
                break;
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println("doGET ACTION= " + action);

        switch (action == null ? "mainPage" : action) {

            case "mainPage":
                viewMainPage(request, response);
                break;

            case "view":
                viewBook(request, response);
                break;
        }
    }

    void viewMainPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("start of viewMainPage method");
        BookService bookServicesImpl = BookServicesImpl.getInstance();
        List<Book> listOfBooks = bookServicesImpl.getListOfBooks();
        request.setAttribute("listOfBooks", listOfBooks);
        System.out.println("end of viewMainPage method");

        System.out.println("end-end of viewMainPage method");
        request.getRequestDispatcher("WEB-INF/views/mainPage.jsp").forward(request, response);
    }

    void viewBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("START OF view METHOD");


        String type = request.getParameter("type");

        if (type.equals("new")) {

            request.setAttribute("actionOnPage", "add");
            request.getRequestDispatcher("WEB-INF/views/bookPage.jsp").forward(request, response);

        } else if (type.equals("existing")){
            System.out.println("1 OF view METHOD");


            int id = Integer.parseInt(request.getParameter("id"));

            BookService bookServicesImpl = BookServicesImpl.getInstance();
            System.out.println("2 OF view METHOD");

            Book book = bookServicesImpl.getBook(id);

            System.out.println("3 OF view METHOD");


            request.setAttribute("book", book);
            request.setAttribute("actionOnPage", "update");
            System.out.println("end OF view METHOD");

            request.getRequestDispatcher("WEB-INF/views/bookPage.jsp").forward(request, response);
        }
    }

    void updateBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("START OF UPDATE METHOD");
        BookServicesImpl bookServicesImpl = BookServicesImpl.getInstance();

        String title = request.getParameter("title");
        String publisher = request.getParameter("publisher");
        String author = request.getParameter("author");
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println("MIDDLE OF UPDATE METHOD");

        bookServicesImpl.updateBook(title, publisher, author, id);
        System.out.println("END OF UPDATE METHOD");
        viewMainPage(request, response);
    }

    void removeBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookServicesImpl bookServicesImpl = BookServicesImpl.getInstance();

        bookServicesImpl.removeBook(Integer.parseInt(request.getParameter("id")));
        viewMainPage(request, response);
    }

    void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookServicesImpl bookServicesImpl = BookServicesImpl.getInstance();
        System.out.println("START OF ADD METHOD");

        String title = request.getParameter("title");
        String publisher = request.getParameter("publisher");
        String author = request.getParameter("author");
        System.out.println("middle OF ADD METHOD");

        bookServicesImpl.addBook(title, publisher, author);
        System.out.println("end OF ADD METHOD");

        request.setAttribute("action", null);
        viewMainPage(request, response);
    }
}