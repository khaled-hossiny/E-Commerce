//package service;
//
//import entity.Product;
//import entity.User;
//import org.hibernate.Query;
//import org.hibernate.Session;
//import utility.HibernateUtil;
//
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Root;
//import java.util.List;
//
//public class UserServiceImp implements UserService{
//    private Session session= HibernateUtil.getSessionFactory().openSession();
//
//    @Override
//    public int addUser(User user) {
//        session.beginTransaction();
//        session.save(user);
//        session.getTransaction().commit();
//        return user.getUserId();
//    }
//
//    @Override
//    public User getUserById(int userId) {
//        User user= session.get(User.class, userId);
//        return user;    }
//
//    @Override
//    public List<User> getAllUsers() {
//        CriteriaBuilder cb = session.getCriteriaBuilder();
//        CriteriaQuery<User> cq = cb.createQuery(User.class);
//        Root<User> root = cq.from(User.class);
//        cq.select(root);
//        Query<User> query = session.createQuery(cq);
//        return query.getResultList();
//    }
//
//    @Override
//    public void editUser(int userId, User user) {
//        session.beginTransaction();
//        User newUser = session.byId(User.class).load(userId);
//        newUser.setUserName(user.getUserName());
//        newUser.setUserPassword(user.getUserPassword());
//        newUser.setUserEmail(user.getUserEmail());
//        newUser.setUserBirthDate(user.getUserBirthDate());
//        newUser.setUserJob(user.getUserJob());
//        newUser.setUserAddress(user.getUserAddress());
//        newUser.setUserCredit(user.getUserCredit());
//        newUser.setUserCart(user.getUserCart());
//        session.merge(user);
//        session.getTransaction().commit();
//
//    }
//
//    @Override
//    public void deleteUser(int userId) {
//        session.beginTransaction();
//        User user = session.byId(User.class).load(userId);
//        session.delete(user);
//        session.getTransaction().commit();
//
//    }
//}
