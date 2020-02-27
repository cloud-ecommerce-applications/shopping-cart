package com.cloud.shoppingcart.cart.controller;

import com.cloud.shoppingcart.cart.dto.ShoppingCartDTO;
import com.cloud.shoppingcart.cart.model.CartItemEntity;
import com.cloud.shoppingcart.cart.model.ShoppingCartEntity;
import com.cloud.shoppingcart.cart.service.ShoppingService;
import com.cloud.shoppingcart.product.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RunWith(MockitoJUnitRunner.class)
public class ShoppingCartControllerTest {

    @InjectMocks
    private ShoppingCartController shoppingCartController;

    @Mock
    private ShoppingService service;


    Set<ShoppingCartEntity> carts;

    @Before
    public void before(){
        carts = new HashSet<ShoppingCartEntity>();
        Set< CartItemEntity > items = new HashSet<>();
        ShoppingCartEntity cart = new ShoppingCartEntity();
        cart.setCartId(UUID.randomUUID());
        cart.setCustomerId("1");
        for (int i = 1; i <= 5; i++) {
            CartItemEntity item = new CartItemEntity();
            item.setId(UUID.randomUUID());
            item.setSku("sku" + i);
            item.setUnitCost(10);
            items.add(item);

        }
        cart.setItems(items);
        carts.add(cart);
    }

    @Test
    public void allCartsTest(){
        when(service.getAll()).thenReturn(carts);
        assertFalse(shoppingCartController.allCarts().getBody().isEmpty());
    }
}
