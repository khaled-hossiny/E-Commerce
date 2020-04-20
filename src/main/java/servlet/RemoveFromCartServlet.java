package servlet;

import exceptions.ProductNotInShoppingCartException;
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

@WebServlet("/webUSer/remove")
public class RemoveFromCartServlet extends HttpServlet {
    @Inject
    BuyerSession buyerSession;

    @Inject
    BuyerService buyerService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        int quantity = 1;
        if(req.getParameter("quantity") != null) {
            quantity = Integer.parseInt(req.getParameter("quantity"));
        }
        try {
            buyerService.removeProductFromShoppingCart(buyerSession.getBuyer(), id, quantity);
        } catch (ProductNotInStockException e) {
            e.printStackTrace();
        } catch (ProductNotInShoppingCartException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("home");
    }
}
