package entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "ForeignKeyAssoCartEntity")
@Table(name = "cart", uniqueConstraints = {
        @UniqueConstraint(columnNames = "ID")})
public class Cart implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Cart_ID", unique = true, nullable = false)
    private int cartId ;

    @Column(name = "Total_Cost" , nullable = false)
    private long totalCostOfCart ;

    @ManyToOne
    private Product product;

    public Cart() {
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public long getTotalCostOfCart() {
        return totalCostOfCart;
    }

    public void setTotalCostOfCart(long totalCostOfCart) {
        this.totalCostOfCart = totalCostOfCart;
    }
}
