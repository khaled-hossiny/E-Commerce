package service;

import entity.Product;
import entity.User;
import org.hibernate.Query;
import org.hibernate.Session;
import utility.HibernateUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


public class UserServiceImp implements UserService{
    private Session session= HibernateUtil.getSessionFactory().openSession();

    @Override
    public int addUser(User user) {
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        return user.getId();
    }

    @Override
    public User getUserById(int userId) {
        User user= session.get(User.class, userId);
        return user;    }

    @Override
    public boolean validateLogin(String email, String password) {
        User user = null ;
        user = session.get(User.class, email);
        if (user != null && user.getPassword().equals(password)) {
            return true;
        }
         else return false;
    }

    @Override
    public List<User> getAllUsers() {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        cq.select(root);
        Query<User> query = session.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public void editUser(int userId, User user) {
        session.beginTransaction();
        User newUser = session.byId(User.class).load(userId);
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setPassword(user.getPassword());
        newUser.setEmail(user.getEmail());
        newUser.setAddress(user.getAddress());
        session.merge(user);
        session.getTransaction().commit();

    }

    @Override
    public void deleteUser(int userId) {
        session.beginTransaction();
        User user = session.byId(User.class).load(userId);
        session.delete(user);
        session.getTransaction().commit();

    }


}
