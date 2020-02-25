package com.cloud.shoppingcart.cart.dto;

import com.cloud.shoppingcart.cart.model.CartItemEntity;
import com.cloud.shoppingcart.cart.model.ShoppingCartEntity;

import java.util.Set;
import java.util.stream.Collectors;

public class ShoppingCartDTO {
    private String customerId;
    private Set<CartItemDTO> items;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Set<CartItemDTO> getItems() {
        return items;
    }

    public void setItems(Set<CartItemDTO> items) {
        this.items = items;
    }

    public ShoppingCartEntity toEntity(){
        ShoppingCartEntity entity = new ShoppingCartEntity();
        entity.setCustomerId(this.getCustomerId());
        entity.setItems(this.getItems().stream().map(CartItemDTO::toEntity).collect(Collectors.toSet()));
        return entity;
    }
}
