package entity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USER",uniqueConstraints = {
        @UniqueConstraint(columnNames = "user_id"),
        @UniqueConstraint(columnNames = "user_email") })
public class User implements java.io.Serializable {
    private int userId;
    private String userName;
    private String userPassword;
    private String userEmail;
    private Date userBirthDate;
    private String userJob;
    private String userAddress;
    private Credit userCredit;
    private Cart userCart ;
    private Set<UserBuyProduct> userProducts = new HashSet<UserBuyProduct>(0);


    public User() {
    }

    public User(int userId, String userName, String userEmail, String userPassword, Date userBirthDate, String userJob, String userAddress,Credit userCredit,Cart userCart,Set<UserBuyProduct> userProducts) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.userBirthDate=userBirthDate;
        this.userJob = userJob;
        this.userAddress = userAddress;
        this.userCredit=userCredit;
        this.userCart=userCart;
        this.userProducts = userProducts;
    }

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "user_id" ,  unique = true, nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Column(name = "user_name", nullable = false, length = 50)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    @Column(name = "user_password", nullable = false, length = 50)
    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    @Column(name = "user_email",unique = true,nullable = false,length = 50)
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    @Temporal(TemporalType.DATE)
    @Column(name = "user_birth_date",nullable = false, length=10)
    public Date getUserBirthDate() {
        return userBirthDate;
    }

    public void setUserBirthDate(Date userBirthDate) {
        this.userBirthDate = userBirthDate;
    }

    @Column(name = "user_job",unique = true,nullable = false,length = 50)
    public String getUserJob() {
        return userJob;
    }

    public void setUserJob(String userJob) {
        this.userJob = userJob;
    }
    public String getUserAddress() {
        return userAddress;
    }
    @Column(name = "user_address",nullable = false,length = 50)
    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="credit_id")
    public Credit getUserCredit() {
        return userCredit;
    }

    public void setUserCredit(Credit userCredit) {
        this.userCredit = userCredit;
    }



    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.USER", cascade=CascadeType.ALL)
    public Set<UserBuyProduct> getUserProducts() {
        return userProducts;
    }

    public void setUserProducts(Set<UserBuyProduct> userProducts) {
        this.userProducts = userProducts;
    }






    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="Cart_ID")
    public Cart getUserCart() {
        return userCart;
    }

    public void setUserCart(Cart userCart) {
        this.userCart = userCart;
    }


}
