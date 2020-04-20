package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "MyupdateServlet",urlPatterns={"/MyupdateServlet"})
public class MyupdateServlet extends HttpServlet {
    String firstName;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         firstName = request.getParameter("firstName");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out= response.getWriter();
        System.out.println("Name is = " +firstName);
        out.println("Firstt naneeee nouran>>>" +firstName);
    }
}
