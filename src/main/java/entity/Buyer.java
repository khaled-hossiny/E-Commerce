package entity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Buyer extends User {

    private CreditCard creditCardById;
    private ShoppingCart shoppingCartsById;
    private Set<UserBuyProduct> userBuyProductsById = new HashSet<>();
    

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
