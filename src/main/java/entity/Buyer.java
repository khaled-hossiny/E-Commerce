package entity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Buyer extends User implements Serializable {


    private CreditCard creditCardById;
    private ShoppingCart shoppingCartsById;
    private Set<UserBuyProduct> userBuyProductsById = new HashSet<>();

    public Buyer(String address, String firstName, String lastName, String email, String password) {
        super(address,firstName ,lastName , email, password);
    }

    public Buyer() {

    }


    @OneToOne(mappedBy = "buyer")
    public CreditCard getCreditCardById() {
        return creditCardById;
    }

    public void setCreditCardById(CreditCard creditCardById) {
        this.creditCardById = creditCardById;
    }

    @OneToOne(mappedBy = "buyerByBuyerId")
    public ShoppingCart getShoppingCartsById() {
        return shoppingCartsById;
    }

    public void setShoppingCartsById(ShoppingCart shoppingCartsById) {
        this.shoppingCartsById = shoppingCartsById;
    }

    @OneToMany(mappedBy = "pk.buyer")
    public Set<UserBuyProduct> getUserBuyProductsById() {
        return userBuyProductsById;
    }

    public void setUserBuyProductsById(Set<UserBuyProduct> userBuyProductsById) {
        this.userBuyProductsById = userBuyProductsById;
    }
}
