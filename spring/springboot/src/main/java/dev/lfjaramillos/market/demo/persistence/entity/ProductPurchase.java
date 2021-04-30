package dev.lfjaramillos.market.demo.persistence.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table
public class ProductPurchase {


    @EmbeddedId
    private ProductPurchasePK id;

    @Column(name = "cantidad")
    private BigDecimal quantity;

    @Column(name = "total")
    private Integer total;

    @Column(name = "estado")
    private boolean state;

    @ManyToOne
    @JoinColumn(name = "id_compra",insertable = false, updatable = false)
    private Purchases purchase;

    @ManyToOne
    @JoinColumn(name = "id_producto",insertable = false, updatable = false)
    private ProductEntity productEntity;

    public ProductPurchasePK getId() {
        return id;
    }

    public void setId(ProductPurchasePK id) {
        this.id = id;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
