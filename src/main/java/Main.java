import entity.Credit;
import entity.User;
import org.hibernate.Session;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Session session = HibernateUril.getSessionFactory().openSession();
        session.beginTransaction();
        Credit credit=new Credit();
        credit.setCreditNumber(123);
        credit.setCreditBalance(1000);
        User user=new User();
        user.setUserName("shimaa");
        user.setUserBirthDate(new Date());
        user.setUserEmail("alshimaa@gmail.com");
        user.setUserAddress("octobar");
        user.setUserCredit(credit);
        user.setUserPassword("1234");
        user.setUserJob("developer");
        session.save(credit);
        session.save(user);
        session.getTransaction().commit();
        HibernateUril.shutdown();

    }
}
