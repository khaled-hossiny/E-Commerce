package service;

import entity.*;
import exceptions.NotEnoughCreditException;
import exceptions.ProductNotInShoppingCartException;
import exceptions.ProductNotInStockException;

import java.util.List;
import java.util.Set;

public interface BuyerService extends UserService{
    CartProduct addProductToShoppingCart(Buyer buyer, int productId, int quantity) throws ProductNotInStockException;
    CartProduct removeProductFromShoppingCart(Buyer buyer, int productId, int quantity) throws ProductNotInStockException, ProductNotInShoppingCartException;
    Set<UserBuyProduct> buy(Buyer buyer) throws NotEnoughCreditException;
    List<Product> searchProduct (String searchName);
}
