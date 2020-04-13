package entity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Buyer extends User {
//    private Integer id;
//    private User userById;
    private CreditCard creditCardById;
    private Set<ShoppingCart> shoppingCartsById = new HashSet<>();
    private Set<UserBuyProduct> userBuyProductsById = new HashSet<>();

//    @Id
//    @Column(name = "id", nullable = false)
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Buyer buyer = (Buyer) o;
//        return Objects.equals(id, buyer.id);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id);
//    }
//
//    @OneToOne
//    @JoinColumn(name = "id", referencedColumnName = "id", nullable = false)
//    public User getUserById() {
//        return userById;
//    }
//
//    public void setUserById(User userById) {
//        this.userById = userById;
//    }

    @OneToOne(mappedBy = "buyer")
    public CreditCard getCreditCardById() {
        return creditCardById;
    }

    public void setCreditCardById(CreditCard creditCardById) {
        this.creditCardById = creditCardById;
    }

    @OneToMany(mappedBy = "buyerByBuyerId")
    public Set<ShoppingCart> getShoppingCartsById() {
        return shoppingCartsById;
    }

    public void setShoppingCartsById(Set<ShoppingCart> shoppingCartsById) {
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
