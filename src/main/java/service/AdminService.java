package service;

import entity.Product;
import entity.User;

import java.util.List;

public interface AdminService {
    int addProduct(Product product);
    Product getProductById(int productId);
    List<Product> getAllProducts();
    void editProduct(long productId, Product product);
    void deleteProduct(long productId);
    User viewCustomerProfile(int userId);
}
