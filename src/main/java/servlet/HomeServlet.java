package servlet;

import entity.Category;
import entity.Product;
import service.BuyerService;
import service.BuyerServiceImpl;
import utility.BuyerSession;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/webUSer/home")
public class HomeServlet extends HttpServlet {
    @Inject
    BuyerService buyerService;

    @Inject
    BuyerSession buyerSession;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String searchQuery = req.getParameter("searchQuery");
        List<Product> products;

        if (searchQuery != null) {
            products = buyerService.searchProduct(searchQuery);
        } else {
            products = buyerService.getAllProducts();
        }
        req.setAttribute("products", products);

        req.getRequestDispatcher("products.jsp").forward(req, resp);
    }
}
