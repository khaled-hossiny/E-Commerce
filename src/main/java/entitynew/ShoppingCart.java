package entitynew;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "shopping_cart")
public class ShoppingCart {
    @Id
    private int cartID;

    @OneToOne
    @MapsId
    private Buyer buyer;
    @OneToMany(
            mappedBy = "cart",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<CartProduct> productSet = new HashSet<>();

    public ShoppingCart() {
    }

}
