package servlet;
import java.io.File;
import java.io.IOException;

import entity.Product;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import service.AdminService;
import service.AdminServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

public class EditProduct extends HttpServlet {
    File file=null;
    AdminServiceImpl adminService=new AdminServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        String desc = request.getParameter("desc");
        String price = request.getParameter("price");
        String stock = request.getParameter("stock");

        if(ServletFileUpload.isMultipartContent(request)){
            try {
                List <FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
                for(FileItem item : multiparts){
                    if(!item.isFormField()){
                        String names = new File(item.getName()).getName();
                        file=new File("E://upload" + File.separator + names);
                        log("File is "+file.getAbsolutePath());
                        System.out.println("File Path is "+file.getAbsolutePath());
                        item.write(file);
                    }
                }
                log("file"+file.getAbsolutePath());
            } catch (Exception ex) {
                log(ex.getMessage());
                ex.printStackTrace();
            }
        }else{

            System.err.println("Fail");
        }

        Product product=new Product();
        product.setName(name);
        product.setQuantity(Integer.parseInt(stock));
        product.setPrice(Integer.parseInt(price));
        product.setDescription(desc);
        product.setImage(file.getAbsolutePath());
        adminService.addProduct(product);

        request.getRequestDispatcher("/products.jsp").forward(request, response);

    }
}
