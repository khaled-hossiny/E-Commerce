package service;

import entity.Buyer;
import entity.CartProduct;
import entity.Product;
import entity.UserBuyProduct;
import exceptions.NotEnoughCreditException;
import exceptions.ProductNotInShoppingCartException;
import exceptions.ProductNotInStockException;

import java.util.Set;

public interface BuyerService {
    CartProduct addProductToShoppingCart(int buyerID, int productId, int quantity) throws ProductNotInStockException;
    CartProduct removeProductFromShoppingCart(int buyerID, int productId, int quantity) throws ProductNotInStockException, ProductNotInShoppingCartException;
    CartProduct removeProductFromShoppingCart(int buyerID, int productId) throws ProductNotInStockException, ProductNotInShoppingCartException;
    Set<UserBuyProduct> buy(int buyerID) throws NotEnoughCreditException;
}
