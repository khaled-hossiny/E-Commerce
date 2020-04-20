
package servlet;

import javax.inject.Inject;
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
import exceptions.ProductAlreadyExistsException;
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
    Set<Category> categorySet = new HashSet<>();
    private static final String UPLOAD_DIRECTORY = PropertiesUtil.uploadsPath();
    // upload settings
    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB
    @Inject
    AdminService adminService;
    private boolean check = true;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // super.doGet(req, resp);
        resp.setContentType("text/html");
        List<Category> categories = new ArrayList<>();
        categories = adminService.getAllCategories();
        req.setAttribute("categories", categories);
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
        String name = "", desc = "", price = "", stock = "", filePath = "", category = "";
        List<Integer> categoryIds = new ArrayList<>();

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
                    } else {
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
                               //selectedStudentIds.add(new String(item.get()));
                                categoryIds.add(Integer.parseInt(new String(item.get())));
                               break;
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
        List<Category> categories = adminService.getCategoriesByIds(categoryIds);
        Product product = new Product();
        product.setName(name);
        product.setQuantity(Integer.parseInt(stock));
        product.setPrice(Integer.parseInt(price));
        product.setDescription(desc);
        product.setImage(filePath);
        product.setCategories(new HashSet<>(categories));
        for(Category cat : categories) {
            cat.getProducts().add(product);
        }
        try {
            adminService.addProduct(product);
            response.sendRedirect("ShowProducts");
        } catch (ProductAlreadyExistsException e) {
            e.printStackTrace();
            request.getRequestDispatcher("ErrorPage.html").include(request, response);
        }

    }


}
