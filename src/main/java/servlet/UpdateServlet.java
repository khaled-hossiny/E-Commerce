package servlet;

import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateServlet",urlPatterns = {"/UpdateServlet"})
public class UpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            User user = (User) request.getSession().getAttribute("user");
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String email = user.getEmail();
        String address = user.getAddress();

        user.getLastName();
        user.getEmail();
        user.getPassword();
        user.getAddress();

        request.setAttribute("firstname",firstName);
        request.setAttribute("lastname",lastName);
        request.setAttribute("email",email);
        request.setAttribute("address",address);


        request.getRequestDispatcher("UpdateProfile.jsp").include(request, response);

    }
}
