package com.cloud.shoppingcart.cart.dto;


import com.cloud.shoppingcart.cart.model.CartItemEntity;
import com.cloud.shoppingcart.cart.model.ShoppingCartEntity;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class ShoppingCartDTOTest {

    @Test
    public void given_cart_entity_when_convert_then_dto_is_same() {
        //Given cart
        Set<CartItemEntity> items = new HashSet<>();
        ShoppingCartEntity cart = new ShoppingCartEntity();
        cart.setCartId(UUID.randomUUID());
        for (int i = 1; i <= 5; i++) {
            CartItemEntity item = new CartItemEntity();
            item.setId(UUID.randomUUID());
            item.setSku("sku" + i);
            item.setUnitCost(10);
            item.setCart(cart);
            items.add(item);

        }
        cart.setCustomerId("user1");
        cart.setItems(items);
        //When convert
        ShoppingCartDTO dto = cart.toDto();
        //Then are same
        assertTrue(cart.equals(dto.toEntity()));

    }

}
