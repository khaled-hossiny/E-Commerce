package entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "USER",uniqueConstraints = {
        @UniqueConstraint(columnNames = "user_id"),
        @UniqueConstraint(columnNames = "user_email") })
public class User {
    private int userId;
    private String userName;
    private String userPassword;
    private String userEmail;
    private Date userBirthDate;
    private String userJob;
    private String userAddress;
    private Credit userCredit;

    public User() {
    }

    public User(int userId, String userName, String userEmail, String userPassword, Date userBirthDate, String userJob, String userAddress,Credit userCredit) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.userBirthDate=userBirthDate;
        this.userJob = userJob;
        this.userAddress = userAddress;
        this.userCredit=userCredit;
    }

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "user_id")
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
    public Credit getUserCredit() {
        return userCredit;
    }

    public void setUserCredit(Credit userCredit) {
        this.userCredit = userCredit;
    }


}
