package servlet;

import com.mysql.cj.log.Log;
import entity.Product;
import service.AdminServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RemoveProduct extends HttpServlet {
    AdminServiceImpl adminService=new AdminServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(request, resp);

        String productId = request.getParameter("id");
       int success= adminService.deleteProduct(Integer.parseInt(productId));
       if(success==1) {
           request.setAttribute("Success","success");
           RequestDispatcher dispatcher = request.getRequestDispatcher("ShowProducts");
           dispatcher.include(request, resp);
       }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
