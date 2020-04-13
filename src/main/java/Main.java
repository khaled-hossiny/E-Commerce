import entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("E-commerce");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Buyer user = new Buyer();
        user.setFirstName("Khaled");
        user.setLastName("ElHossiny");
        user.setAddress("New Cairo");
        user.setEmail("khaled@gmail.com");
        user.setPassword("password");
        entityManager.persist(user);

        CreditCard creditCard = new CreditCard();
        creditCard.setBalance(1000.0);
        creditCard.setBuyer(user);

        entityManager.persist(creditCard);

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setBuyerByBuyerId(user);

        entityManager.persist(shoppingCart);

        Product product = new Product();
        product.setName("Iphone 11");
        product.setQuantity(4);
        product.setPrice(5000);
        product.setDescription("7amada");

        Category category = new Category();
        category.setName("Phones");
        entityManager.persist(category);

        product.getCategories().add(category);
        entityManager.persist(product);

        CartProduct cartProduct = new CartProduct();
        cartProduct.setCart(shoppingCart);
        cartProduct.setProduct(product);
        cartProduct.setQuantity(2);
        shoppingCart.getCartProductsById().add(cartProduct);
        entityManager.persist(cartProduct);

        UserBuyProduct userBuyProduct = new UserBuyProduct();
        userBuyProduct.setBuyer(user);
        userBuyProduct.setProduct(product);
        userBuyProduct.setQuantity(2);

        entityManager.persist(userBuyProduct);

        entityManager.getTransaction().commit();

    }
}
