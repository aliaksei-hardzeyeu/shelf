package by.hardzeyeu.libraryV2.controllers;

import by.hardzeyeu.libraryV2.services.BorrowService;
import by.hardzeyeu.libraryV2.services.implementations.BorrowServicesImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "BorrowServlet", value = "/borrow")
public class BorrowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println("doPOST ACTION= " + action);

        switch (action == null ? "mainPage" : action) {

            case "addBorrow":
                addBorrow(request, response);
                break;

            case "changeStatus":
                changeStatus(request, response);

        }
    }

    void addBorrow(HttpServletRequest request,  HttpServletResponse response) throws ServletException, IOException {
        System.out.println("addborrow 0");

        BorrowService borrowService = BorrowServicesImpl.getInstance();

        System.out.println("addborrow 1");
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        String userName = request.getParameter("name");
        String userEmail = request.getParameter("email");
        int timePeriod = Integer.parseInt(request.getParameter("period"));
        String comment = request.getParameter("comment");
        System.out.println("addborrow 2");

        borrowService.addBorrow(bookId, userName, userEmail, timePeriod, comment);

        System.out.println("addborrow 3");

        response.sendRedirect("http://localhost:8081/?action=view&type=existing&bookId=" + bookId);
    }


    void changeStatus(HttpServletRequest request, HttpServletResponse response) {

    }
}
