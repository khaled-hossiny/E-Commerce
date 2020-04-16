package exceptions;

public class ProductNotInShoppingCartException extends Exception {
    public ProductNotInShoppingCartException(String message) {
        super(message);
    }
}
