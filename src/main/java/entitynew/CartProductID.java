package entitynew;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CartProductID implements Serializable {
    @Column(name = "cart_id")
    private int cartID;
    @Column(name = "product_id")
    private int productID;

    public CartProductID() {
    }

    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartProductID)) return false;
        CartProductID that = (CartProductID) o;
        return cartID == that.cartID &&
                productID == that.productID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartID, productID);
    }
}
