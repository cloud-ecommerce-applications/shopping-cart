package com.cloud.shoppingcart.cart.model;

import com.cloud.shoppingcart.cart.dto.CartItemDTO;

import java.util.Objects;

public class CartItemEntity {
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

    public CartItemDTO toDto(){
        CartItemDTO dto = new CartItemDTO();
        dto.setUnitCost(this.getUnitCost());
        dto.setId(this.getId());
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
