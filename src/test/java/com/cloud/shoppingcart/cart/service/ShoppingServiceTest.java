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
import static org.mockito.Mockito.*;

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
            item.setCart(cart);
            items.add(item);

        }
        cart.setItems(items);
        carts.add(cart);
    }

    @Test
    public void given_cart_when_find_then_cart_is_present(){
        when(shoppingCartRepository.findAll()).thenReturn(carts);
        assertTrue(service.getAll().iterator().hasNext());
    }
    @Test
    public void given_cart_when_user_then_cart(){
        String userName = "1";
        when(shoppingCartRepository.findByCustomerId(any())).thenReturn(carts.stream().findFirst());
        assertTrue(service.getCart(userName).isPresent());

    }
    @Test
    public void given_cart_when_add_item_then_item_isAdded(){
        String userName = "1";
        CartItemEntity item = new CartItemEntity();
        item.setSku("sku10");
        item.setUnitCost(10);
        int size_before_add = carts.stream().findFirst()
                .map(ShoppingCartEntity::getItems).orElse(new HashSet<>()).size();

        //Given when cart
        when(shoppingCartRepository.findByCustomerId(any())).thenReturn(carts.stream().findFirst());
        //when add item
        Optional<ShoppingCartEntity> cart = service.addToCart(userName, item);

        //then item added
        assertTrue(cart.isPresent());
        assertTrue(cart.get().getItems().size()==size_before_add+1 );

    }
}
