package entity;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class UserBuyProductPK implements Serializable {
    private Buyer buyer;
    private Product product;

    @ManyToOne(cascade = CascadeType.MERGE)
    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    @ManyToOne(cascade = CascadeType.MERGE)
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
