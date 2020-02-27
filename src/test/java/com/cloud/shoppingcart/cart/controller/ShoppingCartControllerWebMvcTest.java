package com.cloud.shoppingcart.cart.controller;

import com.cloud.shoppingcart.cart.dto.ShoppingCartDTO;
import com.cloud.shoppingcart.cart.model.CartItemEntity;
import com.cloud.shoppingcart.cart.model.ShoppingCartEntity;
import com.cloud.shoppingcart.cart.service.ShoppingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(ShoppingCartController.class)
public class ShoppingCartControllerWebMvcTest {


    private ShoppingCartController shoppingCartController;

    @MockBean
    private ShoppingService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;


    Set<ShoppingCartEntity> carts;

    @Before
    public void before(){
        shoppingCartController = new ShoppingCartController(service);
        carts = new HashSet<ShoppingCartEntity>();
        Set< CartItemEntity > items = new HashSet<>();
        ShoppingCartEntity cart = new ShoppingCartEntity();
        cart.setCustomerId("1");
        for (int i = 1; i <= 5; i++) {
            CartItemEntity item = new CartItemEntity();
            item.setId(1);
            item.setSku("sku" + i);
            item.setUnitCost(10);
            items.add(item);

        }
        cart.setItems(items);
        carts.add(cart);
    }

    @Test
    public void homeTest() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/shopping/")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(request).andReturn();
        assertEquals("shopping home", result.getResponse().getContentAsString());
    }

    @Test
    public void allCartsTest() throws Exception {
        when(service.getAll()).thenReturn(carts);
        RequestBuilder request = MockMvcRequestBuilders.get("/shopping/carts")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(request).andReturn();
        String response= result.getResponse().getContentAsString();
        assertEquals(response, objectMapper.writeValueAsString(carts));
    }


}
