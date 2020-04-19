package service;

import entity.*;
import exceptions.InvalidLoginException;
import utility.HibernateUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@ApplicationScoped
public class UserServiceImp implements UserService {
    protected EntityManager entityManager;

    {
        entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
    }

    @Override
    public Buyer addUser(Buyer buyer) {
        CreditCard creditCard = new CreditCard();
        creditCard.setBalance(10000.0);
        creditCard.setBuyer(buyer);
        buyer.setCreditCardById(creditCard);
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setBuyerByBuyerId(buyer);
        entityManager.getTransaction().begin();
        entityManager.persist(creditCard);
        entityManager.persist(shoppingCart);
        entityManager.persist(buyer);
        entityManager.getTransaction().commit();
        return buyer;
    }

    @Override
    public User getUserById(int userId) {
        User user = entityManager.find(User.class, userId);
        return user;
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
    public List<User> getAllUsers() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        cq.select(root);
        TypedQuery<User> query = entityManager.createQuery(cq);
        return query.getResultList();
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
    public void editUser(int userId, User user) {
        entityManager.getTransaction().begin();
        User newUser = entityManager.find(User.class, userId);
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setPassword(user.getPassword());
        newUser.setEmail(user.getEmail());
        newUser.setAddress(user.getAddress());
        entityManager.merge(user);
        entityManager.getTransaction().commit();

    }

    @Override
    public void deleteUser(int userId) {
        entityManager.getTransaction().begin();
        User user = entityManager.find(User.class, userId);
        entityManager.remove(user);
        entityManager.getTransaction().commit();

    }

    /**
     *
     * @param email
     * @param password
     * @return user of the matching credentials
     * @throws InvalidLoginException if the given credentials aren't valid
     */
    @Override
    public User login(String email, String password) throws InvalidLoginException {


        TypedQuery<User> query = entityManager.createQuery("SELECT u From User u WHERE u.email = :email and u.password = :password", User.class);
        query.setParameter("email", email);
        query.setParameter("password", password);
        List<User> result = query.getResultList();
        if(result.isEmpty()) {
            throw new InvalidLoginException("invalid login or password");
        }
        else
            return  result.get(0) ;
    }
}
