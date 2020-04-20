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
        for (CartProduct cartProduct : cart.getCartProductsById()) {
            if (cartProduct.getProduct().equals(product)) {
                int quantityInCart = cartProduct.getQuantity();
                if (quantityInCart + quantity > product.getQuantity()) {
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
    public CartProduct removeProductFromShoppingCart(Buyer buyer, int productId, int quantity) throws ProductNotInShoppingCartException {
        Product product = entityManager.find(Product.class, productId);
        entityManager.getTransaction().begin();
        ShoppingCart cart = buyer.getShoppingCartsById();
        entityManager.merge(buyer);
        entityManager.merge(cart);
        for (CartProduct cartProduct : cart.getCartProductsById()) {
            if (cartProduct.getProduct().equals(product)) {
                int quantityInCart = cartProduct.getQuantity();
                int updatedQuantity = quantityInCart - quantity;
                if (updatedQuantity <= 0) {
                    String hql = "delete from CartProduct where pk.product = :product and pk.cart = :cart";
                    Query query = entityManager.createQuery(hql);
                    query.setParameter("product", product);
                    query.setParameter("cart", cart);
                    query.executeUpdate();
                } else {
                    cartProduct.setQuantity(updatedQuantity);
                    cart.calculateTotalCost();
                    entityManager.merge(cartProduct);
                }
                entityManager.getTransaction().commit();
                entityManager.refresh(buyer);
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
        for (CartProduct cartProduct : cart.getCartProductsById()) {
            if (cartProduct.getProduct().equals(product)) {
                entityManager.remove(cartProduct);
                cart.calculateTotalCost();
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
            entityManager.merge(product);
            entityManager.persist(userBuyProduct);
            return userBuyProduct;
        }).collect(Collectors.toSet());
        buyer.setUserBuyProductsById(purchases);
        clearShoppingCart(buyer);
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

    private void clearShoppingCart(Buyer buyer) {
        ShoppingCart shoppingCart = buyer.getShoppingCartsById();
        String hql = "delete from CartProduct where pk.cart = :cart";
        Query query = entityManager.createQuery(hql);
        query.setParameter("cart", shoppingCart);
        query.executeUpdate();
    }
}
