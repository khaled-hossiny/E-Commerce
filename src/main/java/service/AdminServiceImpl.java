
package service;

import entity.Product;
import entity.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import utility.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import entity.*;

public class AdminServiceImpl implements AdminService {


    private EntityManager entityManager;

    {
        entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
    }

    @Override
    public int addProduct(Product product) {
        entityManager.getTransaction().begin();
        entityManager.persist(product);
        entityManager.getTransaction().commit();
        return product.getId();
    }

    @Override
    public Product getProductById(int productId) {
        Product product = entityManager.find(Product.class, productId);
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> root = cq.from(Product.class);
        cq.select(root);
        TypedQuery<Product> query = entityManager.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public void editProduct(int id, Product product) {
        entityManager.getTransaction().begin();
        Product prod = entityManager.find(Product.class, id);
        prod.setPrice(product.getPrice());
        prod.setDescription(product.getDescription());
        prod.setName(product.getName());
        prod.setQuantity(product.getQuantity());
        prod.setCategories(product.getCategories());
        prod.setCartProductsById(product.getCartProductsById());
        prod.setImage(product.getImage());
        entityManager.merge(prod);
        entityManager.getTransaction().commit();


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
    public List<Category> getAllCategory() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Category> cq = cb.createQuery(Category.class);
        Root<Category> root = cq.from(Category.class);
        cq.select(root);
        TypedQuery<Category> query = entityManager.createQuery(cq);
        System.out.println("size is "+query.getResultList().size());
        return query.getResultList();
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
