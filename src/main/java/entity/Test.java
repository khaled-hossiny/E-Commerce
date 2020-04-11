package entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Test {
    public static void main(String[] args) throws ParseException {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        User user = new User();
        user.setUserEmail("karima988@gmail.com");
        user.setUserPassword("123456");
        user.setUserName("karima");
        user.setUserJob("developer");
        String sDate1="1/1/1997";
        Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
        user.setUserBirthDate(date1);
        //user.setUserCart(cart);
        session.save(user);

        ///////////////////////////////////////////////////////////////////////////////////
        Product product = new Product();
        product.setProductName("dress");
        product.setProductDiscription("tweel");
        product.setProductQuantity(100);
        product.setProductCost(5000);
        product.setSale(50);

        Product product2 = new Product();
        product2.setProductName("skirt");
        product2.setProductDiscription("tweela");
        product2.setProductQuantity(10);
        product2.setProductCost(520);
        product2.setSale(40);

        session.save(product);
        session.save(product2);

        //////////////////////////////////////////////////////////////
        Credit credit = new Credit();
        credit.setCreditBalance(9000);
        credit.setCreditNumber(14575120);
        session.save(credit);
        /////////////////////////////////////////////////////////////////////

        Cart cart = new Cart();
        Set products = new HashSet<>();
        products.add(product);
        products.add(product2);
        cart.setProducts(products);
        session.save(cart);

        /////////////////////////////////////////////////////////////////////////////

        UserBuyProduct userBuyProduct = new UserBuyProduct();
        userBuyProduct.setUser(user);
        userBuyProduct.setProduct(product);
        userBuyProduct.setUserID(user.getUserId());
        userBuyProduct.setProductID(product.getProductId());
        userBuyProduct.setUserCart(cart);



        session.beginTransaction();
        session.getTransaction().commit();
        //System.out.println("Donnnnne");


    }
}
