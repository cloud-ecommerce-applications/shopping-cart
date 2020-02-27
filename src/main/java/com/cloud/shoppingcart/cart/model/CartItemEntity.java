package com.cloud.shoppingcart.cart.model;

import com.cloud.shoppingcart.cart.dto.CartItemDTO;
import com.sun.javafx.beans.IDProperty;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "CART_ITEM")
public class CartItemEntity {

     @Id
     private UUID id;

     @Column
     private String sku;

     @Column
     private int qty;

     @Column(name="UNIT_COST")
     private double unitCost;

    @ManyToOne
    @JoinColumn(name="cartId", nullable=false)
    private ShoppingCartEntity cart;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(double unitCost) {
        this.unitCost = unitCost;
    }

    public ShoppingCartEntity getCart() {
        return cart;
    }

    public void setCart(ShoppingCartEntity cart) {
        this.cart = cart;
    }

    public CartItemDTO toDto(){
        CartItemDTO dto = new CartItemDTO();
        dto.setUnitCost(this.getUnitCost());
        dto.setId(this.getId());
        dto.setCartId(this.getCart().getCartId());
        dto.setQty(this.getQty());
        dto.setSku(this.getSku());
        return dto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartItemEntity)) return false;
        CartItemEntity entity = (CartItemEntity) o;
        return getId() == entity.getId() &&
                getQty() == entity.getQty() &&
                Double.compare(entity.getUnitCost(), getUnitCost()) == 0 &&
                Objects.equals(getSku(), entity.getSku());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSku(), getQty(), getUnitCost());
    }
}
