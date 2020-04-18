package service;

import entity.*;
import exceptions.NotEnoughCreditException;
import exceptions.ProductNotInShoppingCartException;
import exceptions.ProductNotInStockException;
import utility.HibernateUtil;

import javax.persistence.EntityManager;
import java.util.Set;
import java.util.stream.Collectors;

public class BuyerServiceImpl implements BuyerService{
    private EntityManager entityManager;

    {
        entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
    }

    @Override
    public CartProduct addProductToShoppingCart(int buyerID, int productId, int quantity) throws ProductNotInStockException {
        Buyer buyer = entityManager.find(Buyer.class, buyerID);
        Product product = entityManager.find(Product.class, productId);
        entityManager.getTransaction().begin();
        ShoppingCart cart = buyer.getShoppingCartsById();
        for(CartProduct cartProduct : cart.getCartProductsById()) {
            if(cartProduct.getProduct().equals(product)) {
                int quantityInCart = cartProduct.getQuantity();
                if(quantityInCart + quantity > product.getQuantity()) {
                    throw new ProductNotInStockException("product not in stock");
                }
                cartProduct.setQuantity(quantityInCart + quantity);
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
    public CartProduct removeProductFromShoppingCart(int buyerID, int productId, int quantity) throws ProductNotInShoppingCartException {
        Buyer buyer = entityManager.find(Buyer.class, buyerID);
        Product product = entityManager.find(Product.class, productId);
        entityManager.getTransaction().begin();
        ShoppingCart cart = buyer.getShoppingCartsById();
        for(CartProduct cartProduct : cart.getCartProductsById()) {
            if(cartProduct.getProduct().equals(product)) {
                int quantityInCart = cartProduct.getQuantity();
                int updatedQuantity = quantityInCart - quantity;
                if(updatedQuantity <= 0) {
                    entityManager.remove(cartProduct);
                }
                else {
                    cartProduct.setQuantity(updatedQuantity);
                    cart.calculateTotalCost();
                }
                entityManager.getTransaction().commit();
                return cartProduct;
            }
        }
        throw new ProductNotInShoppingCartException("product not found in shopping cart");
    }

    @Override
    public CartProduct removeProductFromShoppingCart(int buyerID, int productId) throws ProductNotInShoppingCartException {
        Buyer buyer = entityManager.find(Buyer.class, buyerID);
        Product product = entityManager.find(Product.class, productId);
        entityManager.getTransaction().begin();
        ShoppingCart cart = buyer.getShoppingCartsById();
        for(CartProduct cartProduct : cart.getCartProductsById()) {
            if(cartProduct.getProduct().equals(product)) {
                entityManager.remove(cartProduct);
                cart.calculateTotalCost();
                entityManager.getTransaction().commit();
                return cartProduct;
            }
        }
        throw new ProductNotInShoppingCartException("product not found in shopping cart");
    }

    @Override
    public Set<UserBuyProduct> buy(int buyerID) throws NotEnoughCreditException {
        Buyer buyer = entityManager.find(Buyer.class, buyerID);
        double balance = buyer.getCreditCardById().getBalance();
        int cost = buyer.getShoppingCartsById().geTotalCost();
        if(cost > balance) {
            throw new NotEnoughCreditException("you don't have enough balance to buy those items");
        }
        entityManager.getTransaction().begin();
        buyer.getCreditCardById().setBalance(balance - cost);
        Set<UserBuyProduct> purchases = buyer.getShoppingCartsById().getCartProductsById().stream().map(cartProduct -> {
            Product product = cartProduct.getProduct();
            UserBuyProduct userBuyProduct = new UserBuyProduct();
            userBuyProduct.setBuyer(buyer);
            userBuyProduct.setQuantity(cartProduct.getQuantity());
            userBuyProduct.setProduct(product);
            int updatedQuantity = product.getQuantity() - cartProduct.getQuantity();
            product.setQuantity(updatedQuantity);
            entityManager.persist(product);
            entityManager.persist(userBuyProduct);
            entityManager.remove(cartProduct);
            return userBuyProduct;
        }).collect(Collectors.toSet());
        buyer.setUserBuyProductsById(purchases);
        entityManager.getTransaction().commit();
        return purchases;
    }
}
