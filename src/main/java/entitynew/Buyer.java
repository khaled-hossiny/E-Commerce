package entitynew;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Buyer extends User {
    private int id;

    @OneToOne(mappedBy = "buyer", cascade = CascadeType.ALL)
    private ShoppingCart cart;

    @OneToOne(mappedBy = "buyer", cascade = CascadeType.ALL)
    private CreditCard creditCard;

    @OneToMany(
            mappedBy = "buyer",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<BuyerBuyProduct> buyProducts = new HashSet<>();

    public Buyer() {
    }

    public Buyer(int userId, String userName, String userEmail, String userPassword, Date userBirthDate, String userJob, String userAddress) {
        super(userId, userName, userEmail, userPassword, userBirthDate, userJob, userAddress);
    }

}
