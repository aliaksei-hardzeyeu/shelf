package by.hardzeyeu.libraryV2.controllers;

import by.hardzeyeu.libraryV2.services.BorrowService;
import by.hardzeyeu.libraryV2.services.Utils;
import by.hardzeyeu.libraryV2.services.implementations.BorrowServicesImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;

@WebServlet(name = "BorrowServlet", value = "/BorrowServlet")
public class BorrowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BorrowService borrowService = BorrowServicesImpl.getInstance();

        int bookId = Integer.parseInt(request.getParameter("bookId"));
        String userName = request.getParameter("name");
        String userEmail = request.getParameter("email");
        LocalDate borrowDate = LocalDate.parse(request.getParameter("borrowDate"));
        int timePeriod = Integer.parseInt(request.getParameter("period"));
        String comment = request.getParameter("comment");

        borrowService.addBorrow(bookId, userName, userEmail, borrowDate, timePeriod, comment);

        request.setAttribute("actionOnPage", "view");

        request.getRequestDispatcher("/").forward(request, response);
    }
}
