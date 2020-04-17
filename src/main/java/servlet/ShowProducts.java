package servlet;

import entity.Product;
import service.AdminService;
import service.AdminServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ShowProducts extends HttpServlet {
    AdminService adminService=new AdminServiceImpl();
    List<Product> list=new ArrayList<>();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        PrintWriter out = resp.getWriter();
        log("DO GET");
        list=adminService.getAllProducts();
        req.setAttribute("list", list);
        RequestDispatcher ry = req.getRequestDispatcher("products.jsp");
        ry.include(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        PrintWriter out = resp.getWriter();
        list=adminService.getAllProducts();
        req.setAttribute("list", list);
        RequestDispatcher ry = req.getRequestDispatcher("products.jsp");
        ry.include(req, resp);
    }
}
