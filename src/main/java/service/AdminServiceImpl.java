package service;

import entity.Product;
import entity.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import utility.HibernateUtil;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class AdminServiceImpl implements AdminService {
    private Session session = HibernateUtil.getSessionFactory().openSession();

    @Override
    public int addProduct(Product product) {
        session.beginTransaction();
        session.save(product);
        session.getTransaction().commit();
        return product.getId();
    }

    @Override
    public Product getProductById(int productId) {
        Product product = session.get(Product.class, productId);
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> root = cq.from(Product.class);
        cq.select(root);
        TypedQuery<Product> query = session.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public void editProduct(long id, Product product) {
        session.beginTransaction();
        Product prod = session.byId(Product.class).load(id);
        prod.setPrice(product.getPrice());
        prod.setDescription(product.getDescription());
        prod.setName(product.getName());
        prod.setQuantity(product.getQuantity());
        prod.setCategories(product.getCategories());
        prod.setCartProductsById(product.getCartProductsById());
        session.merge(prod);
        session.getTransaction().commit();


    }

    @Override
    public void deleteProduct(long id) {
        session.beginTransaction();
        Product product = session.byId(Product.class).load(id);
        session.delete(product);
        session.getTransaction().commit();
    }

    @Override
    public User viewCustomerProfile(int userId) {
        User user = session.get(User.class, userId);
        return user;
    }
}
