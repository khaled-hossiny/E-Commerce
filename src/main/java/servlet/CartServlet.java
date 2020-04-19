package servlet;

import entity.Buyer;
import entity.CartProduct;
import entity.ShoppingCart;
import utility.BuyerSession;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@WebServlet("/webUSer/cart")
public class CartServlet extends HttpServlet {
    @Inject BuyerSession buyerSession;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Buyer buyer = buyerSession.getBuyer();
        ShoppingCart shoppingCart = buyer.getShoppingCartsById();
        Set<CartProduct> cartProducts = shoppingCart.getCartProductsById();
        req.setAttribute("cartProducts", cartProducts);
        req.getRequestDispatcher("cart.jsp").forward(req, resp);
    }
}
