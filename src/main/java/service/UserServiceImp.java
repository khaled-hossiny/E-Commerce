package service;

import entity.Product;
import entity.User;
import exceptions.InvalidLoginException;
import org.hibernate.Transaction;
import utility.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


public class UserServiceImp implements UserService {
    private EntityManager entityManager;

    {
        entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
    }

    @Override
    public User addUser(User user) {
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        return user;
    }

    @Override
    public User getUserById(int userId) {
        User user = entityManager.find(User.class, userId);
        return user;
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
    public void editUser(User user) {

        entityManager.getTransaction().begin();
        //entityManager.merge(u)
//        User newUser = entityManager.find(User.class, userId);
//        newUser.setFirstName(user.getFirstName());
//        newUser.setLastName(user.getLastName());
//        newUser.setPassword(user.getPassword());
//        newUser.setEmail(user.getEmail());
//        newUser.setAddress(user.getAddress());
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

    @Override
    public List<Product> searchProduct(String searchName) {
        Query query = entityManager.createQuery("SELECT e FROM Product e where e.name LIKE :searchName " );
        query.setParameter("searchName" , "%"+searchName+"%");
        System.out.println("the serchName is " +searchName);
        List<Product> resultList = query.getResultList();
        //resultList.forEach(System.out::println);
        return resultList ;
    }
}
