package entitynew;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Entity
public class CreditCard {
    @Id
    private int id;
    private String cardNum;
    private double balance;
    @OneToOne
    @MapsId
    private Buyer buyer;
}
