package service;

import entity.Product;
import entity.User;
import exceptions.InvalidLoginException;

import java.util.List;

public interface UserService {
    User addUser(User user);
    User getUserById(int userId);
    List<User> getAllUsers();
    void editUser( User user);
    void deleteUser(int userId);

    User login(String email, String password) throws InvalidLoginException;

    List<Product> searchProduct (String searchName);
}
