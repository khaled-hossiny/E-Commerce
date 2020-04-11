package entitynew;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Category {
    @Id
    @Column(name = "category_id")
    private int id;
    private String categoryName;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "product_category",
            joinColumns = {@JoinColumn(name = "category_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")}
    )
    private Set<Product> productSet = new HashSet<>();
}
