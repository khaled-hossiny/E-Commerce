package entity;

import javax.persistence.*;

@Entity
@Table(name = "user_buy_product")
public class UserBuyProduct {
    private int userID ;
    private int ProductID ;
    private User user;
    private Product product;
    private long quantityOfEachProduct ;
    private Cart cartId;


    public UserBuyProduct() {
    }

    public UserBuyProduct(User user, Product product) {
        this.user = user;
        this.product = product;
        //this.cart = cart;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Column(name = "quantityOfEachProduct")
    public long getQuantityOfEachProduct() {
        return quantityOfEachProduct;
    }

    public void setQuantityOfEachProduct(long quantityOfEachProduct) {
        this.quantityOfEachProduct = quantityOfEachProduct;
    }

    @Column(name = "user_ID")
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Column(name = "product_ID")
    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int productID) {
        ProductID = productID;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="Cart_ID")
    public Cart getUserCart() {
        return cartId;
    }

    public void setUserCart(Cart userCart) {
        this.cartId = userCart;
    }
}
