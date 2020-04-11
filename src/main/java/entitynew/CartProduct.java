package entitynew;

import javax.persistence.*;

@Entity
public class CartProduct {
    @EmbeddedId
    private CartProductID id;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("cartID")
    private ShoppingCart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productID")
    private Product product;
    private int quantity;

}
