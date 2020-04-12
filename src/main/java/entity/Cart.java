package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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

    private Set<CartContainProduct> cartContainProducts = new HashSet<CartContainProduct>(0);



    public Cart() {
    }

    public Cart(long totalCostOfCart, Set<CartContainProduct> cartContainProducts) {
        this.totalCostOfCart = totalCostOfCart;
        this.cartContainProducts = cartContainProducts;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CART_ID" ,unique = true ,  nullable = false )
    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    @Column(name = "CART_Total_COST" ,  nullable = false )
    public long getTotalCostOfCart() {
        return totalCostOfCart;
    }

    public void setTotalCostOfCart(long totalCostOfCart) {
        this.totalCostOfCart = totalCostOfCart;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.cart", cascade=CascadeType.ALL)

    public Set<CartContainProduct> getCartContainProducts() {
        return cartContainProducts;
    }

    public void setCartContainProducts(Set<CartContainProduct> cartContainProducts) {
        this.cartContainProducts = cartContainProducts;
    }
}
