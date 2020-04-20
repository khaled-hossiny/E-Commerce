package servlet;

import exceptions.NotEnoughCreditException;
import service.BuyerService;
import utility.BuyerSession;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/webUSer/checkout")
public class CheckOutServlet extends HttpServlet {
    @Inject
    BuyerService buyerService;

    @Inject
    BuyerSession buyerSession;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            buyerService.buy(buyerSession.getBuyer());
        } catch (NotEnoughCreditException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("home");
    }
}
