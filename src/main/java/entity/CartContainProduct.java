package entity;


import javax.persistence.*;

@Entity
@Table(name = "CART_Contaion_Product")
@AssociationOverrides({
        @AssociationOverride(name = "pk.cart",
                joinColumns = @JoinColumn(name = "CART_ID")),
        @AssociationOverride(name = "pk.product",
                joinColumns = @JoinColumn(name = "product_ID")) })
public class CartContainProduct implements java.io.Serializable  {
    private CartContainProductId pk = new CartContainProductId();
    private long quantity ;



    @EmbeddedId
    public CartContainProductId getPk() {
        return pk;
    }

    public void setPk(CartContainProductId pk) {
        this.pk = pk;
    }

    @Transient
    public Cart getCart() {
        return getPk().getCart();
    }

    public void setCart(Cart cart) {
        getPk().setCart(cart);
    }

    @Transient
    public Product getProduct() {
        return getPk().getProduct();
    }

    public void setProduct(Product product) {
        getPk().setProduct(product);
    }

    @Column(name = "Quantity_PRoduct", nullable = false)
    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        CartContainProduct that = (CartContainProduct) o;

        if (getPk() != null ? !getPk().equals(that.getPk())
                : that.getPk() != null)
            return false;

        return true;
    }

    public int hashCode() {
        return (getPk() != null ? getPk().hashCode() : 0);
    }
}
