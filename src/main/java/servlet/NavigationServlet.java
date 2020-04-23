package servlet;

import entity.Buyer;
import entity.CartProduct;
import entity.Category;
import entity.ShoppingCart;
import service.BuyerService;
import utility.BuyerSession;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@WebServlet("/nav")
public class NavigationServlet extends HttpServlet {
    @Inject
    BuyerSession buyerSession;
    @Inject
    BuyerService buyerService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> categories=buyerService.getAllCategories();
        Buyer buyer = buyerSession.getBuyer();
        ShoppingCart shoppingCart = buyer.getShoppingCartsById();
        shoppingCart.calculateTotalCost();
        int cartCost = shoppingCart.geTotalCost();
        Set<CartProduct> cartProducts = shoppingCart.getCartProductsById();
        int cartSize = cartProducts.stream().mapToInt(cartProduct -> cartProduct.getQuantity()).sum();
        double balance = buyer.getCreditCardById().getBalance();
        req.setAttribute("cartCost", cartCost);
        req.setAttribute("cartProducts", cartProducts);
        req.setAttribute("cartSize", cartSize);
        req.setAttribute("balance", balance);
        req.setAttribute("categories",categories);
        req.getRequestDispatcher("webUSer/common.jsp").include(req, resp);
    }
}
