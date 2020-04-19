package servlet;

import entity.Product;
import service.BuyerService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/webUSer/product")
public class ProductServlet extends HttpServlet {
    @Inject BuyerService buyerService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Product product = buyerService.getProductById(id);
        req.setAttribute("product", product);
        req.getRequestDispatcher("product-page.jsp").forward(req, resp);
    }
}
