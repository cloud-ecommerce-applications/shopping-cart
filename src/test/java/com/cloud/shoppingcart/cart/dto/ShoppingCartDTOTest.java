package com.cloud.shoppingcart.cart.dto;


import com.cloud.shoppingcart.cart.model.CartItemEntity;
import com.cloud.shoppingcart.cart.model.ShoppingCartEntity;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

public class ShoppingCartDTOTest {

    @Test
    public void test_dto_entity_conversion() {
        Set<CartItemEntity> items = new HashSet<>();
        ShoppingCartEntity cart = new ShoppingCartEntity();
        for (int i = 1; i <= 5; i++) {
            CartItemEntity item = new CartItemEntity();
            item.setId(1);
            item.setSku("sku" + i);
            item.setUnitCost(10);
            items.add(item);

        }
        cart.setCustomerId("user1");
        cart.setItems(items);
        ShoppingCartDTO dto = cart.toDto();
        assertTrue(cart.equals(dto.toEntity()));

    }

}
