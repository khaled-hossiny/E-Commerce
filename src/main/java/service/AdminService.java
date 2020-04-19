package service;

import entity.Category;
import entity.Product;
import entity.User;

import java.util.List;

public interface AdminService {
    int addProduct(Product product);
    Product getProductById(int productId);
    List<Product> getAllProducts();
    void editProduct(int productId, Product product);
    int deleteProduct(int productId);
    User viewCustomerProfile(int userId);
    List<Category> getAllCategory();
     //int deleteCategory(int parseInt);
    int addCategory(Category category);
}
