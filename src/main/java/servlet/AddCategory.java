package servlet;

import entity.Category;
import service.AdminServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AddCategory extends HttpServlet {
    boolean check=true;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AdminServiceImpl adminService=new AdminServiceImpl();
        List<Category> categoryList=adminService.getAllCategories();
        System.out.println("size"+categoryList.size());
        Category category=new Category();
        category.setName(req.getParameter("name"));
        System.out.println("catttttt"+req.getParameter("name"));
        for(int i=0;i<categoryList.size();i++){
            if(category.getName().equals(categoryList.get(i).getName())) {
                check = false;
                break;
            }
        }
        if(check==true){
            adminService.addCategory(category);
            req.setAttribute("isSucceed", "sucess");

//            RequestDispatcher dispatcher = req.getRequestDispatcher("ShowProducts");
//            dispatcher.forward(req, resp);
            resp.sendRedirect("ShowProducts");
        }else{
            RequestDispatcher dispatcher = req.getRequestDispatcher("ErrorPage.html");
            dispatcher.forward(req, resp);
        }
    }
}
