
package service;

import entity.Product;
import entity.User;
import exceptions.ProductAlreadyExistsException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import utility.HibernateUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import entity.*;

@ApplicationScoped
public class AdminServiceImpl extends UserServiceImp implements AdminService {


    @Override
    public int addProduct(Product product) {
        entityManager.getTransaction().begin();
        entityManager.persist(product);
        entityManager.getTransaction().commit();
        return product.getId();
    }

    @Override
    public void editProduct(int id, Product product) throws ProductAlreadyExistsException {
        entityManager.getTransaction().begin();
        Product prod = entityManager.find(Product.class, id);
        prod.setPrice(product.getPrice());
        prod.setDescription(product.getDescription());
        prod.setName(product.getName());
        prod.setQuantity(product.getQuantity());
        prod.setCategories(product.getCategories());
        prod.setCartProductsById(product.getCartProductsById());
        if(product.getImage() != null)
            prod.setImage(product.getImage());
        try {
            entityManager.persist(prod);
            entityManager.getTransaction().commit();
        }
        catch (RollbackException e) {
            e.printStackTrace();
            throw new ProductAlreadyExistsException("product already exists");
        }


    }

    @Override
    public int deleteProduct(int id) {
        entityManager.getTransaction().begin();
        Product product = entityManager.find(Product.class, id);
        entityManager.remove(product);
        entityManager.getTransaction().commit();
        if(entityManager.contains(product)){
            return 0;
        }else{
            return 1;
        }
    }

    @Override
    public User viewCustomerProfile(int userId) {
        User user = entityManager.find(User.class, userId);
        return user;
    }

    @Override
    public int addCategory(Category category) {
        entityManager.getTransaction().begin();
        entityManager.persist(category);
        entityManager.getTransaction().commit();
        return category.getId();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

//    public int deleteCategory(int parseInt) {
//        entityManager.getTransaction().begin();
//        Category category = entityManager.find(Category.class, parseInt);
//
//        entityManager.remove(category);
//        entityManager.getTransaction().commit();
//        if(entityManager.contains(category)){
//            return 0;
//        }else{
//            return 1;
//        }
//
//    }
}
