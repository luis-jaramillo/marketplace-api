package dev.lfjaramillos.marketplace.demo.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class ProductPurchasePK implements Serializable {


    @Column(name = "id_compra")
    private Integer purchaseId;

    @Column(name = "id_producto")
    private Integer productId;

    public Integer getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Integer purchaseId) {
        this.purchaseId = purchaseId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}
