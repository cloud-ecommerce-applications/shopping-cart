package com.cloud.shoppingcart.cart.dto;

import com.cloud.shoppingcart.cart.model.CartItemEntity;

import java.util.Objects;
import java.util.UUID;

public class CartItemDTO {
     private UUID id;
     private String sku;
     private int qty;
     private double unitCost;
     private UUID cartId ;

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
    public UUID getCartId() {
        return cartId;
    }

    public void setCartId(UUID cartId) {
        this.cartId = cartId;
    }

    public CartItemEntity toEntity(){
        CartItemEntity entity = new CartItemEntity();
        entity.setId(this.getId());
        //TODO set the cart id
       // entity.setCart();
        entity.setQty(this.getQty());
        entity.setSku(this.getSku());
        entity.setUnitCost(this.getUnitCost());
        return entity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartItemDTO)) return false;
        CartItemDTO that = (CartItemDTO) o;
        return getId() == that.getId() &&
                getQty() == that.getQty() &&
                Double.compare(that.getUnitCost(), getUnitCost()) == 0 &&
                Objects.equals(getSku(), that.getSku()) &&
                getCartId().equals(that.getCartId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSku(), getQty(), getUnitCost(), getCartId());
    }
}
