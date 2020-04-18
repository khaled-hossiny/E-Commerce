package servlet;

import entity.Admin;
import entity.Product;
import entity.User;
import service.AdminService;
import service.AdminServiceImpl;
import service.UserService;
import service.UserServiceImp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ShowUsers extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);

        UserService userService = new UserServiceImp();
        List<User> list = userService.getAllUsers();
        PrintWriter out = resp.getWriter();
        log("DO GET");
        req.setAttribute("list", list);
        RequestDispatcher ry = req.getRequestDispatcher("showUsers.jsp");
        ry.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       // super.doPost(req, resp);
        PrintWriter out = resp.getWriter();
        UserService userService = new UserServiceImp();
        List<User> list = userService.getAllUsers();
        req.setAttribute("list", list);
        RequestDispatcher ry = req.getRequestDispatcher("showUsers.jsp");
        ry.forward(req, resp);
    }
}
