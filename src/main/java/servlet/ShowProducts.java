package servlet;

import entity.Category;
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


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        AdminService adminService=new AdminServiceImpl();
        PrintWriter out = resp.getWriter();
        List<Product> list=new ArrayList<>();
        List<Category> categories=new ArrayList<>();
        log("DO GET");
        list=adminService.getAllProducts();
        categories=adminService.getAllCategory();
        System.out.println("name" +categories.get(0).getName());
        req.setAttribute("list", list);
        req.setAttribute("categories",categories);
        RequestDispatcher ry = req.getRequestDispatcher("products.jsp");
        ry.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        AdminService adminService=new AdminServiceImpl();
        List<Product> list=new ArrayList<>();
        PrintWriter out = resp.getWriter();
        list=adminService.getAllProducts();
        req.setAttribute("list", list);
        RequestDispatcher ry = req.getRequestDispatcher("products.jsp");
        ry.forward(req, resp);
    }
}
