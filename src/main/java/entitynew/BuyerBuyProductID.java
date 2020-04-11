package entitynew;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BuyerBuyProductID implements Serializable {
    @Column(name = "buyer_id")
    private int buyerID;
    @Column(name = "product_id")
    private int productID;

    public BuyerBuyProductID() {
    }

    public int getBuyerID() {
        return buyerID;
    }

    public void setBuyerID(int buyerID) {
        this.buyerID = buyerID;
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
        if (!(o instanceof BuyerBuyProductID)) return false;
        BuyerBuyProductID that = (BuyerBuyProductID) o;
        return buyerID == that.buyerID &&
                productID == that.productID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(buyerID, productID);
    }
}
