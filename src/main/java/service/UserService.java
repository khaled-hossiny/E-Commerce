package service;

import entity.Buyer;
import entity.Category;
import entity.Product;
import entity.User;
import exceptions.InvalidLoginException;

import java.util.List;

public interface UserService {
    Buyer addUser(Buyer buyer);
    User getUserById(int userId);
    List<User> getAllUsers();
    void editUser( User user);
    void deleteUser(int userId);
    User login(String email, String password) throws InvalidLoginException;
    Product getProductById(int productId);
    List<Product> getAllProducts();
    List<Category> getAllCategory();
}
