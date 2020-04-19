package servlet;

import exceptions.ProductNotInStockException;
import service.BuyerService;
import utility.BuyerSession;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/webUSer/add")
public class AddToCartServlet extends HttpServlet {
    @Inject
    BuyerService buyerService;

    @Inject
    BuyerSession buyerSession;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        int quantity = 1;
        if(req.getParameter("quantity") != null) {
            quantity = Integer.parseInt(req.getParameter("quantity"));
        }
        try {
            buyerService.addProductToShoppingCart(buyerSession.getBuyer(), id, quantity);
        } catch (ProductNotInStockException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("home");
    }
}
