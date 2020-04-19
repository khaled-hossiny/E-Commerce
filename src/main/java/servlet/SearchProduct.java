package servlet;

import entity.Product;
import service.UserServiceImp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/search")
public class SearchProduct extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserServiceImp userServiceImp = new UserServiceImp();
        String name = req.getParameter("searchInput");
        System.out.println("the input search is " +name);
        List<Product> productList = userServiceImp.searchProduct(name);
        for(Product pro : productList) {
            System.out.println(pro.getName());
        }
        req.setAttribute("list", productList);
        RequestDispatcher ry = req.getRequestDispatcher("products.jsp");
        ry.forward(req, resp);

    }
}
