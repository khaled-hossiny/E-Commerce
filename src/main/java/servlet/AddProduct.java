
package servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

import entity.Category;

import entity.Product;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import service.AdminService;
import service.AdminServiceImpl;
import utility.PropertiesUtil;

import java.util.*;

@MultipartConfig
public class AddProduct extends HttpServlet {
    private static final long serialVersionUID = 1L;
    Set<Category> categorySet=new HashSet<>();
    private static final String UPLOAD_DIRECTORY = PropertiesUtil.uploadsPath();
    // upload settings
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
    AdminServiceImpl adminService = new AdminServiceImpl();
    private boolean check=true;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       // super.doGet(req, resp);
        resp.setContentType("text/html");
        AdminService adminService=new AdminServiceImpl();
        List<Category> categories=new ArrayList<>();
        categories=adminService.getAllCategory();
        req.setAttribute("categories",categories);
        RequestDispatcher ry = req.getRequestDispatcher("add-product.jsp");
        ry.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // configures upload settings
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // sets memory threshold - beyond which files are stored in disk
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // sets temporary location to store files
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload = new ServletFileUpload(factory);

        // sets maximum size of upload file
        upload.setFileSizeMax(MAX_FILE_SIZE);

        // sets maximum size of request (include file + form data)
        upload.setSizeMax(MAX_REQUEST_SIZE);

        // constructs the directory path to store upload file
        // this path is relative to application's directory
//        String uploadPath = getServletContext().getRealPath("")
//                + File.separator + UPLOAD_DIRECTORY;

        String uploadPath = UPLOAD_DIRECTORY;

        // creates the directory if it does not exist
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        String name = "", desc = "", price = "", stock = "", filePath = "",category="";

        try {
            // parses the request's content to extract file data
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(request);

            if (formItems != null && formItems.size() > 0) {
                // iterates over form's fields
                for (FileItem item : formItems) {
                    // processes only fields that are not form fields
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        filePath = uploadPath + File.separator + fileName;
                        File fileStore = new File(filePath);

                        // saves the file on disk
                        item.write(fileStore);
                    }
                    else {
                        switch (item.getFieldName()) {
                            case "name":
                                name = new String(item.get());
                                break;
                            case "desc":
                                desc = new String(item.get());
                                break;
                            case "stock":
                                stock = new String(item.get());
                                break;
                            case "category":
                                category=new String(item.get());
                            default:
                                price = new String(item.get());
                        }
                    }
                }
            }
        } catch (Exception ex) {
            request.setAttribute("message",
                    "There was an error: " + ex.getMessage());
        }

        Product product = new Product();
        product.setName(name);
        product.setQuantity(Integer.parseInt(stock));
        product.setPrice(Integer.parseInt(price));
        product.setDescription(desc);
        product.setImage(filePath);
        Category categoryChoose=new Category();
        categoryChoose.setName(category);

        //System.out.println("categgggggggggg"+CategoryType.getType(Integer.parseInt(category)));

        List<Product> products=adminService.getAllProducts();


        for (int i = 0; i<products.size(); i++){
            if(products.get(i).getName().equals(name)){
                check=false;
                break;
            }
        }
        if(check==true) {
            adminService.addProduct(product);
            product.setCategories(categorySet);
            request.getRequestDispatcher("ShowProducts").include(request, response);
        }else{
            request.getRequestDispatcher("ErrorPage.html").include(request, response);
        }

    }


}
