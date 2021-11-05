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
    private final BookService bookServicesImpl;

    public BookServlet() {
        this.bookServicesImpl = BookServicesImpl.getInstance();
    }

    public BookServlet(BookService bookServiceImpl) {
        this.bookServicesImpl = bookServiceImpl;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action == null ? "mainPage" : action) {

            case "update" -> {
                String title = request.getParameter("title");
                String publisher = request.getParameter("publisher");
                String author = request.getParameter("author");
                int id = Integer.parseInt(request.getParameter("id"));
                bookServicesImpl.updateBook(title, publisher, author, id);

                response.sendRedirect("/");
            }

            case "remove" -> {
                bookServicesImpl.removeBook(Integer.parseInt(request.getParameter("id")));

                response.sendRedirect("/");
            }

            case "add" -> {
                String title = request.getParameter("title");
                String publisher = request.getParameter("publisher");
                String author = request.getParameter("author");
                bookServicesImpl.addBook(title, publisher, author);

                response.sendRedirect("/");
            }
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action == null ? "mainPage" : action) {

            case "mainPage" -> {
                List<Book> listOfBooks = bookServicesImpl.getListOfBooks();
                request.setAttribute("listOfBooks", listOfBooks);

                request.getRequestDispatcher("WEB-INF/views/mainPage.jsp").forward(request, response);
            }

            case "addNew" -> request.getRequestDispatcher("WEB-INF/views/addNew.jsp").forward(request, response);

            case "update" -> {
                int id = Integer.parseInt(request.getParameter("id"));
                Book book = bookServicesImpl.getBook(id);
                request.setAttribute("book", book);

                request.getRequestDispatcher("WEB-INF/views/bookPage.jsp").forward(request, response);
            }
        }
    }
}