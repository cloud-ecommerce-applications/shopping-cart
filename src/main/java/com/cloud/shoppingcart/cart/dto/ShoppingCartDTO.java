package com.cloud.shoppingcart.cart.dto;

import com.cloud.shoppingcart.cart.model.CartItemEntity;
import com.cloud.shoppingcart.cart.model.ShoppingCartEntity;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class ShoppingCartDTO {
    private UUID cartId ;
    private String customerId;
    private Set<CartItemDTO> items;

    public UUID getCartId() {
        return cartId;
    }

    public void setCartId(UUID cartId) {
        this.cartId = cartId;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShoppingCartDTO)) return false;
        ShoppingCartDTO that = (ShoppingCartDTO) o;
        return getCartId().equals(that.getCartId()) &&
                getCustomerId().equals(that.getCustomerId()) &&
                Objects.equals(getItems(), that.getItems());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCartId(), getCustomerId(), getItems());
    }

    public ShoppingCartEntity toEntity(){
        ShoppingCartEntity entity = new ShoppingCartEntity();
        entity.setCartId(this.getCartId());
        entity.setCustomerId(this.getCustomerId());
        entity.setItems(this.getItems().stream().map(CartItemDTO::toEntity).collect(Collectors.toSet()));
        return entity;
    }

}
