package com.cloud.shoppingcart.cart.model;

import com.cloud.shoppingcart.cart.dto.CartItemDTO;

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
}
