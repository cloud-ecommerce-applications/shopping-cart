package com.cloud.shoppingcart.cart.model;

import com.cloud.shoppingcart.cart.dto.ShoppingCartDTO;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class ShoppingCartEntity {
    private String customerId;
    private Set<CartItemEntity> items;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Set<CartItemEntity> getItems() {
        return items;
    }

    public void setItems(Set<CartItemEntity> items) {
        this.items = items;
    }

    public ShoppingCartDTO toDto(){
        ShoppingCartDTO dto = new ShoppingCartDTO();
        dto.setCustomerId(this.getCustomerId());
        dto.setItems(this.getItems().stream().map(CartItemEntity::toDto).collect(Collectors.toSet()));
        return dto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShoppingCartEntity)) return false;
        ShoppingCartEntity that = (ShoppingCartEntity) o;
        return Objects.equals(getCustomerId(), that.getCustomerId()) &&
                Objects.equals(getItems(), that.getItems());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCustomerId(), getItems());
    }
}
