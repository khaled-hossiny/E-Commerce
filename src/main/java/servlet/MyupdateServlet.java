package servlet;

import entity.Buyer;
import entity.User;
import service.UserServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "MyupdateServlet",urlPatterns={"/MyupdateServlet"})
public class MyupdateServlet extends HttpServlet {
    String firstName;;
    String address ;
    String lastName;
    String email;
    String password ;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         firstName = request.getParameter("firstName");
        lastName = request.getParameter("lastName");
        email = request.getParameter("email");
        password = request.getParameter("password");
        address = request.getParameter("address");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        UserServiceImp dao = new UserServiceImp();
        //User userObject = new Buyer();
        user.setAddress(address);
        user.setLastName(lastName);
        user.setFirstName(firstName);
        user.setPassword(password);
        user.setEmail(email);
        System.out.println("User is my update = "+ user.getId());
        System.out.println("User Dao = " +user);
      dao.editUser(user);

        response.sendRedirect("HomeServlet");



    }
}
