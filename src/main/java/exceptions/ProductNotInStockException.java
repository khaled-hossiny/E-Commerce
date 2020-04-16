package exceptions;

public class ProductNotInStockException extends Exception{
    public ProductNotInStockException(String message) {
        super(message);
    }
}
