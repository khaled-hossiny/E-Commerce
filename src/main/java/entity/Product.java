package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "product" , uniqueConstraints = {
        @UniqueConstraint(columnNames = "product_ID"),  @UniqueConstraint(columnNames = "product_Name")})
public class Product implements Serializable {
    private int productId ;
    private String productName ;
    private String productDiscription ;
    //image of Product
    private long productCost ;
    private long productQuantity ;
    private long sale ;
    //rate of product

    /*
    @ManyToMany
    private Set<User> users  ;

     */


    public Product() {
    }

    public Product(String productName, String productDiscription, long productCost, long productQuantity, long sale) {
        this.productName = productName;
        this.productDiscription = productDiscription;
        this.productCost = productCost;
        this.productQuantity = productQuantity;
        this.sale = sale;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_ID" , unique = true ,  nullable = false)
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Column (name = "product_Name" , nullable = false ,  length = 100)
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Column (name = "product_Disc" , nullable = false ,  length = 100)
    public String getProductDiscription() {
        return productDiscription;
    }

    public void setProductDiscription(String productDiscription) {
        this.productDiscription = productDiscription;
    }

    @Column (name = "product_Cost" , nullable = false)
    public long getProductCost() {
        return productCost;
    }

    public void setProductCost(long productCost) {
        this.productCost = productCost;
    }

    @Column (name = "product_Quantity" , nullable = false)
    public long getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(long productQuantity) {
        this.productQuantity = productQuantity;
    }

    @Column (name = "product_Sale" , nullable = false)
    public long getSale() {
        return sale;
    }

    public void setSale(long sale) {
        this.sale = sale;
    }

}
