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
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "BookServlet", value = "/")
public class BookServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

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

        switch (action == null ? "mainPage" : action) {

            case "mainPage":
                viewMainPage(request, response);
                break;

            case "view":
                try {
                    viewBook(request, response);

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
        }
    }


    /**
     * Sets list of books as attribute, forwards to starting page
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */

    void viewMainPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookService bookServicesImpl = BookServicesImpl.getInstance();
        List<Book> listOfBooks = bookServicesImpl.getListOfBooks();

        request.setAttribute("listOfBooks", listOfBooks);

        request.getRequestDispatcher("WEB-INF/views/mainPage.jsp").forward(request, response);
    }


    /**
     * Allows: in case of action = "new" - add new book, in case of action = "existing" - view and edit existing book.
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     */

    void viewBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String type = request.getParameter("type");

        if (type.equals("new")) {

            request.setAttribute("actionOnPage", "add");
            request.getRequestDispatcher("WEB-INF/views/bookPage.jsp").forward(request, response);

        } else if (type.equals("existing")) {
            int id = Integer.parseInt(request.getParameter("id"));
            BookService bookServicesImpl = BookServicesImpl.getInstance();

            Book book = bookServicesImpl.getBook(id);

            request.setAttribute("book", book);
            request.setAttribute("actionOnPage", "update");

            request.getRequestDispatcher("WEB-INF/views/bookPage.jsp").forward(request, response);
        }
    }


    /**
     * Takes new book properties from book form and updates old ones.
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */

    void updateBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookServicesImpl bookServicesImpl = BookServicesImpl.getInstance();

        String title = request.getParameter("title");
        String publisher = request.getParameter("publisher");
        String author = request.getParameter("author");
        int id = Integer.parseInt(request.getParameter("id"));

        bookServicesImpl.updateBook(title, publisher, author, id);
        viewMainPage(request, response);
    }


    /**
     * Removes book with specified id.
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */

    void removeBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookServicesImpl bookServicesImpl = BookServicesImpl.getInstance();

        bookServicesImpl.removeBook(Integer.parseInt(request.getParameter("id")));
        viewMainPage(request, response);
    }


    /**
     * Adds new book from "new book" form into DB.
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */

    void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookServicesImpl bookServicesImpl = BookServicesImpl.getInstance();

        String title = request.getParameter("title");
        String publisher = request.getParameter("publisher");
        String author = request.getParameter("author");

        bookServicesImpl.addBook(title, publisher, author);

        response.sendRedirect("/");
    }
}