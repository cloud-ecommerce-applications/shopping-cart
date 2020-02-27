package com.cloud.shoppingcart.cart.dto;

import com.cloud.shoppingcart.cart.model.CartItemEntity;

import java.util.Objects;

public class CartItemDTO {
     private int id;
     private String sku;
     private int qty;
     private double unitCost;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public CartItemEntity toEntity(){
        CartItemEntity entity = new CartItemEntity();
        entity.setId(this.getId());
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
                Objects.equals(getSku(), that.getSku());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSku(), getQty(), getUnitCost());
    }
}
