package com.cloud.shoppingcart.cart.service;


import com.cloud.shoppingcart.cart.model.CartItemEntity;
import com.cloud.shoppingcart.cart.model.ShoppingCartEntity;
import com.cloud.shoppingcart.cart.repository.ShoppingCartRepository;
import com.cloud.shoppingcart.product.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ShoppingServiceTest {

    @InjectMocks
    private ShoppingService service;

    @Mock
    private ShoppingCartRepository shoppingCartRepository;

    @Mock
    private ProductService productService;

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
    public void getAllTest(){
        when(shoppingCartRepository.findAll()).thenReturn(carts);
        assertFalse(service.getAll().isEmpty());
    }
    @Test
    public void getCartTest(){
        String userName = "1";
        when(shoppingCartRepository.findAll()).thenReturn(carts);
        when(productService.getUnitCost(any())).thenReturn((double) 10);
        assertTrue(service.getCart(userName).isPresent());

    }
    @Test
    public void addToCartTest(){
        String userName = "1";
        CartItemEntity item = new CartItemEntity();
        item.setSku("sku10");
        item.setUnitCost(10);


        when(shoppingCartRepository.findAll()).thenReturn(carts);
        when(productService.getUnitCost(any())).thenReturn((double) 10);
        Optional<ShoppingCartEntity> cart = service.addToCart(userName, item);
        assertTrue(cart.isPresent());
        cart.ifPresent( c-> {
            assertFalse(c.getItems().size() == carts.size()+1);
        });
    }
}
