package entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cart_product", schema = "ecommerce")
@AssociationOverrides({
        @AssociationOverride(name = "pk.product",
                joinColumns = @JoinColumn(name = "product_id")),
        @AssociationOverride(name = "pk.cart",
                joinColumns = @JoinColumn(name = "cart_id")) })
public class CartProduct implements Serializable {
    private Integer quantity;
    private CartProductPK pk = new CartProductPK();

    @EmbeddedId
    public CartProductPK getPk() {
        return pk;
    }

    public void setPk(CartProductPK pk) {
        this.pk = pk;
    }

    @Transient
    public Product getProduct() {
        return getPk().getProduct();
    }

    @Transient
    public ShoppingCart getCart() {
        return getPk().getCart();
    }

    public void setCart(ShoppingCart cart) {
        getPk().setCart(cart);
    }

    public void setProduct(Product product) {
        getPk().setProduct(product);
    }

    @Basic
    @Column(name = "quantity", nullable = false)
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


}
