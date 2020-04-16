import entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import service.AdminServiceImpl;
import service.UserServiceImp;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Set;
import java.util.stream.Collectors;


public class Main {
    static EntityManager entityManager;
    public static void main(String[] args) {
//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("E-commerce");
//        entityManager = entityManagerFactory.createEntityManager();
//        entityManager.getTransaction().begin();
//        Buyer user = createBuyer();
//        Product product = createProduct();
//        addToShoppingCart(user, product, 1);
//        addToShoppingCart(user, product, 3);
//        entityManager.getTransaction().commit();

        UserServiceImp userServiceImp = new UserServiceImp();
        AdminServiceImpl adminService = new AdminServiceImpl();
        System.out.println();
        adminService.addProduct(createProduct());

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

    }

    public static Buyer createBuyer() {
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
        return user;
    }

    public static Product createProduct() {
        Product product = new Product();
        product.setName("Iphone 11");
        product.setQuantity(4);
        product.setPrice(5000);
        product.setDescription("7amada");
        product.setImage("./img/user.png");
       // entityManager.persist(product);
        return product;
    }

    public static CartProduct addToShoppingCart(Buyer buyer, Product product, int quantity) {
        ShoppingCart cart = buyer.getShoppingCartsById();
        for(CartProduct cartProduct : cart.getCartProductsById()) {
            if(cartProduct.getProduct().equals(product)) {
                int quantityInCart = cartProduct.getQuantity();
                cartProduct.setQuantity(quantityInCart + quantity);
                return cartProduct;
            }
        }
        CartProduct cartProduct = new CartProduct();
        cartProduct.setCart(cart);
        cartProduct.setProduct(product);
        cartProduct.setQuantity(quantity);
        cart.getCartProductsById().add(cartProduct);
        product.getCartProductsById().add(cartProduct);
        cart.calculateTotalCost();
        entityManager.persist(cartProduct);
        return cartProduct;
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
