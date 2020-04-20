package utility;

import entity.Buyer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

@SessionScoped
public class BuyerSession implements Serializable {
    private Buyer buyer;

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        System.out.println("setting buyer session buyer");
        this.buyer = buyer;
    }

    @PostConstruct
    private void postConstruct() {
        System.out.println("buyer session post construct");
    }

    @PreDestroy
    private void preDestroy() {
        System.out.println("buyer session pre destroy");
        buyer = null;
    }
}
