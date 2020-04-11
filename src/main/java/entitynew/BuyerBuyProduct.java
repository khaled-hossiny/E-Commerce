package entitynew;

import javax.persistence.*;

@Entity
public class BuyerBuyProduct {
    @EmbeddedId
    private BuyerBuyProductID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("buyerID")
    private Buyer buyer;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productID")
    private Product product;
    private int quantity;

    public BuyerBuyProduct() {
    }

    public BuyerBuyProductID getId() {
        return id;
    }

    public void setId(BuyerBuyProductID id) {
        this.id = id;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
