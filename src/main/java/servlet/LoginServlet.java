package servlet;

import entity.Buyer;
import entity.User;
import exceptions.InvalidLoginException;
import service.UserServiceImp;
import utility.BuyerSession;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserServiceImp loginDao;
    @Inject
    BuyerSession buyerSession;

    public void init() {
        loginDao = new UserServiceImp();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("login.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            authenticate(request, response);
        } catch (InvalidLoginException e) {
            // TODO Auto-generated catch block
            response.sendRedirect("login.jsp");
            e.printStackTrace();
        }
    }

    private void authenticate(HttpServletRequest request, HttpServletResponse response)
            throws InvalidLoginException, ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = loginDao.login(username, password);
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        if (user instanceof Buyer) {
            buyerSession.setBuyer((Buyer) user);
            response.sendRedirect("webUSer/home");
        } else {
            response.sendRedirect("ShowUsers");
        }
    }
}
