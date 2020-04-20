package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "shopping_cart", schema = "ecommerce")
public class ShoppingCart implements Serializable {
    private Integer id;
    private int totalCost;
    private Set<CartProduct> cartProductsById = new HashSet<>();
    private Buyer buyerByBuyerId;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @OneToMany(mappedBy = "pk.cart")
    public Set<CartProduct> getCartProductsById() {
        return cartProductsById;
    }

    public void setCartProductsById(Set<CartProduct> cartProductsById) {
        this.cartProductsById = cartProductsById;
    }

    @ManyToOne
    @JoinColumn(name = "buyer_id", referencedColumnName = "id")
    public Buyer getBuyerByBuyerId() {
        return buyerByBuyerId;
    }

    public void setBuyerByBuyerId(Buyer buyerByBuyerId) {
        this.buyerByBuyerId = buyerByBuyerId;
    }

    @Transient
    public int geTotalCost() {
        return totalCost;
    }

    public void calculateTotalCost() {
        totalCost = cartProductsById.stream()
                .mapToInt(cartProduct -> cartProduct.getQuantity() * cartProduct.getProduct().getPrice())
                .sum();
    }

    @PostPersist //Fired after EntityManager.persist() - probably on commit() or flush() but might be before that
    void onPostPersist() {
        calculateTotalCost();
    }

    @PostLoad //Fired whenever an entity is loaded via SQL Select statements. Note that there's no @PreLoad annotation
    void onPostLoad() {
        calculateTotalCost();
    }


    @PostUpdate//Fired after any operation which has executed a DML Update statement for the entity.
    void onPostUpdate() {
        calculateTotalCost();
    }



}
