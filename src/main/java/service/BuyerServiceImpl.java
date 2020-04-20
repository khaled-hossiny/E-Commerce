package service;

import entity.*;
import exceptions.NotEnoughCreditException;
import exceptions.ProductNotInShoppingCartException;
import exceptions.ProductNotInStockException;
import utility.HibernateUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class BuyerServiceImpl extends UserServiceImp implements BuyerService {
    private EntityManager entityManager;

    {
        entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
    }

    @Override
    public CartProduct addProductToShoppingCart(Buyer buyer, int productId, int quantity) throws ProductNotInStockException {
        Product product = entityManager.find(Product.class, productId);
        entityManager.getTransaction().begin();
        ShoppingCart cart = buyer.getShoppingCartsById();
        entityManager.merge(buyer);
        for (CartProduct cartProduct : cart.getCartProductsById()) {
            if (cartProduct.getProduct().equals(product)) {
                int quantityInCart = cartProduct.getQuantity();
                if (quantityInCart + quantity > product.getQuantity()) {
                    throw new ProductNotInStockException("product not in stock");
                }
                cartProduct.setQuantity(quantityInCart + quantity);
                entityManager.merge(cartProduct);
                entityManager.getTransaction().commit();
                return cartProduct;
            }
        }
        CartProduct cartProduct = new CartProduct();
        cartProduct.setCart(cart);
        cartProduct.setProduct(product);
        cartProduct.setQuantity(quantity);
        cart.getCartProductsById().add(cartProduct);
        product.getCartProductsById().add(cartProduct);
        cart.calculateTotalCost();
        entityManager.persist(cartProduct);
        entityManager.getTransaction().commit();
        return cartProduct;
    }

    @Override
    public CartProduct removeProductFromShoppingCart(Buyer buyer, int productId, int quantity) throws ProductNotInShoppingCartException {
        Product product = entityManager.find(Product.class, productId);
        entityManager.getTransaction().begin();
        ShoppingCart cart = buyer.getShoppingCartsById();
        entityManager.merge(buyer);
        entityManager.merge(cart);
        Iterator<CartProduct> cartProductIterator = cart.getCartProductsById().iterator();
        while (cartProductIterator.hasNext()) {
            CartProduct cartProduct = cartProductIterator.next();
            if (cartProduct.getProduct().equals(product)) {
                int quantityInCart = cartProduct.getQuantity();
                int updatedQuantity = quantityInCart - quantity;
                if (updatedQuantity <= 0) {
                    cart.getCartProductsById().remove(cartProduct);
                    CartProduct original = entityManager.find(CartProduct.class, cartProduct.getPk());
                    entityManager.remove(original);
                } else {
                    cartProduct.setQuantity(updatedQuantity);
                    cart.calculateTotalCost();
                    entityManager.merge(cartProduct);
                }
                entityManager.getTransaction().commit();
                return cartProduct;
            }
        }
        throw new ProductNotInShoppingCartException("product not found in shopping cart");
    }

    @Override
    public Set<UserBuyProduct> buy(Buyer buyer) throws NotEnoughCreditException {
        double balance = buyer.getCreditCardById().getBalance();
        int cost = buyer.getShoppingCartsById().geTotalCost();
        if (cost > balance) {
            throw new NotEnoughCreditException("you don't have enough balance to buy those items");
        }
        CreditCard creditCard = buyer.getCreditCardById();
        creditCard.setBalance(balance - cost);
        ShoppingCart shoppingCart = buyer.getShoppingCartsById();
        entityManager.getTransaction().begin();
        entityManager.merge(buyer);
        entityManager.merge(creditCard);
        entityManager.merge(shoppingCart);
        Set<UserBuyProduct> purchases = buyer.getShoppingCartsById().getCartProductsById().stream().map(cartProduct -> {
            Product product = cartProduct.getProduct();
            UserBuyProduct userBuyProduct = new UserBuyProduct();
            userBuyProduct.setBuyer(buyer);
            userBuyProduct.setQuantity(cartProduct.getQuantity());
            userBuyProduct.setProduct(product);
            int updatedQuantity = product.getQuantity() - cartProduct.getQuantity();
            product.setQuantity(updatedQuantity);
            CartProduct original = entityManager.find(CartProduct.class, cartProduct.getPk());
            entityManager.remove(original);
            entityManager.merge(product);
            entityManager.persist(userBuyProduct);
            return userBuyProduct;
        }).collect(Collectors.toSet());
        buyer.setUserBuyProductsById(purchases);
        shoppingCart.getCartProductsById().clear();
        buyer.getShoppingCartsById().getCartProductsById().clear();
        entityManager.getTransaction().commit();
        return purchases;
    }

    @Override
    public List<Product> searchProduct(String searchName) {
        Query query = entityManager.createQuery("SELECT e FROM Product e where e.name LIKE :searchName ");
        query.setParameter("searchName", "%" + searchName + "%");
        System.out.println("the serchName is " + searchName);
        List<Product> resultList = query.getResultList();
        return resultList;
    }
}
