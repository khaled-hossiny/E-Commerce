import entity.*;
import exceptions.NotEnoughCreditException;
import exceptions.ProductNotInShoppingCartException;
import exceptions.ProductNotInStockException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import service.AdminServiceImpl;
import service.BuyerService;
import service.BuyerServiceImpl;
import service.UserServiceImp;
import utility.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class Main {
    static EntityManager entityManager;
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("E-commerce");
        entityManager = entityManagerFactory.createEntityManager();
//        entityManager.getTransaction().begin();
//        Buyer user = createBuyer();
//        Product product = createProduct();
//        addToShoppingCart(user, product, 1);
//        addToShoppingCart(user, product, 3);
//        entityManager.getTransaction().commit();

        UserServiceImp userServiceImp = new UserServiceImp();
        AdminServiceImpl adminService = new AdminServiceImpl();
        System.out.println();
        //adminService.addProduct(createProduct1());

        //buy(user);


//        CreditCard creditCard = new CreditCard();
//        creditCard.setBalance(1000.0);
//        creditCard.setBuyer(user);
//
//        entityManager.persist(creditCard);
//
//        ShoppingCart shoppingCart = new ShoppingCart();
//        shoppingCart.setBuyerByBuyerId(user);
//
//        entityManager.persist(shoppingCart);
//
//        Product product = new Product();
//        product.setName("Iphone 11");
//        product.setQuantity(4);
//        product.setPrice(5000);
//        product.setDescription("7amada");
//
//        Category category = new Category();
//        category.setName("Phones");
//        entityManager.persist(category);
//
//        product.getCategories().add(category);
//        entityManager.persist(product);
//
//        CartProduct cartProduct = new CartProduct();
//        cartProduct.setCart(shoppingCart);
//        cartProduct.setProduct(product);
//        cartProduct.setQuantity(2);
//        shoppingCart.getCartProductsById().add(cartProduct);
//        entityManager.persist(cartProduct);
//
//        UserBuyProduct userBuyProduct = new UserBuyProduct();
//        userBuyProduct.setBuyer(user);
//        userBuyProduct.setProduct(product);
//        userBuyProduct.setQuantity(2);
//
//        entityManager.persist(userBuyProduct);
//
//        entityManager.getTransaction().commit();
        entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
        createBuyer();
        //createProduct1();
        //createProduct2();

        TypedQuery<User> q = entityManager.createQuery("from User", User.class);
        List<User> userList = q.getResultList();

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
        product.setPrice(5000);
        product.setDescription("7amada");
        product.setImage("./img/user.png");
       // entityManager.persist(product);
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

    public static void buy(Buyer buyer) {
        Set<UserBuyProduct> purchases = buyer.getShoppingCartsById().getCartProductsById().stream().map(cartProduct -> {
            UserBuyProduct userBuyProduct = new UserBuyProduct();
            userBuyProduct.setBuyer(buyer);
            userBuyProduct.setQuantity(cartProduct.getQuantity());
            userBuyProduct.setProduct(cartProduct.getProduct());
            return userBuyProduct;
        }).collect(Collectors.toSet());
        double balance = buyer.getCreditCardById().getBalance();
        int cost = buyer.getShoppingCartsById().geTotalCost();
        buyer.getCreditCardById().setBalance(balance - cost);
        buyer.setUserBuyProductsById(purchases);

    }



}
