package entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "credit_card", schema = "ecommerce")
public class CreditCard {
    private Double balance;
    private Integer buyerId;
    private Buyer buyer;

    @Basic
    @Column(name = "balance", nullable = false, precision = 0)
    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @GenericGenerator(name = "generator", strategy = "foreign",
            parameters = @Parameter(name = "property", value = "buyer"))
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "buyer_id", nullable = false)
    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditCard that = (CreditCard) o;
        return Objects.equals(balance, that.balance) &&
                Objects.equals(buyerId, that.buyerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(balance, buyerId);
    }

    @OneToOne
    @PrimaryKeyJoinColumn
    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyerByBuyerId) {
        this.buyer = buyerByBuyerId;
    }
}
