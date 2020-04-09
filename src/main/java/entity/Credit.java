package entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CREDIT",uniqueConstraints = {
        @UniqueConstraint(columnNames = "credit_id"),
        @UniqueConstraint(columnNames = "credit_number") })
public class Credit {

    private int creditId;
    private double creditBalance;
    private int creditNumber;

    public Credit() {
    }

    public Credit(int creditId, double creditBalance, int creditNumber) {
        this.creditId = creditId;
        this.creditBalance = creditBalance;
        this.creditNumber = creditNumber;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "credit_id")
    public int getCreditId() {
        return creditId;
    }

    public void setCreditId(int creditId) {
        this.creditId = creditId;
    }

    @Column(name = "credit_balance", nullable = false)
    public double getCreditBalance() {
        return creditBalance;
    }

    public void setCreditBalance(double creditBalance) {
        this.creditBalance = creditBalance;
    }

    @Column(name = "credit_number", nullable = false)
    public int getCreditNumber() {
        return creditNumber;
    }

    public void setCreditNumber(int creditNumber) {
        this.creditNumber = creditNumber;
    }
}


