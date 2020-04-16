import entity.*;
import exceptions.NotEnoughCreditException;
import exceptions.ProductNotInShoppingCartException;
import exceptions.ProductNotInStockException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import service.AdminServiceImpl;
import service.BuyerService;
import service.BuyerServiceImpl;
import service.UserServiceImp;
import utility.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Set;
import java.util.stream.Collectors;


public class Main {
    static EntityManager entityManager;
    public static void main(String[] args) {
        entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
        createBuyer();
        createProduct1();
        createProduct2();

//        Buyer khaled = entityManager.find(Buyer.class, 1);
//        Product macbook = entityManager.find(Product.class, 2);
//        Product iphone = entityManager.find(Product.class, 1);
//        BuyerService buyerService = new BuyerServiceImpl();
//        try {
//            buyerService.buy(khaled.getId());
//        } catch (NotEnoughCreditException e) {
//            e.printStackTrace();
//        }
//        System.out.println("");
    }

    public static Buyer createBuyer() {
        entityManager.getTransaction().begin();
        Buyer user = new Buyer();
        user.setFirstName("Khaled");
        user.setLastName("ElHossiny");
        user.setAddress("New Cairo");
        user.setEmail("khaled@gmail.com");
        user.setPassword("password");
        CreditCard creditCard = new CreditCard();
        creditCard.setBalance(10000.0);
        user.setCreditCardById(creditCard);
        creditCard.setBuyer(user);
        ShoppingCart cart = new ShoppingCart();
        user.setShoppingCartsById(cart);
        cart.setBuyerByBuyerId(user);
        entityManager.persist(user);
        entityManager.persist(cart);
        entityManager.persist(creditCard);
        entityManager.getTransaction().commit();
        return user;
    }

    public static Product createProduct1() {
        entityManager.getTransaction().begin();
        Product product = new Product();
        product.setName("Iphone 11");
        product.setQuantity(4);
        product.setPrice(15000);
        product.setDescription("iphone");
        entityManager.persist(product);
        entityManager.getTransaction().commit();
        return product;
    }

    public static Product createProduct2() {
        entityManager.getTransaction().begin();
        Product product = new Product();
        product.setName("MacBook Air");
        product.setQuantity(2);
        product.setPrice(10000);
        product.setDescription("macbook");
        entityManager.persist(product);
        entityManager.getTransaction().commit();
        return product;
    }



}
