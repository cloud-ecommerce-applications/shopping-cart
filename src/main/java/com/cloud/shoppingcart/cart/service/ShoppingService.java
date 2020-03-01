package com.cloud.shoppingcart.cart.service;

import java.util.*;
import java.util.stream.StreamSupport;

import com.cloud.shoppingcart.cart.model.CartItemEntity;
import com.cloud.shoppingcart.cart.model.ShoppingCartEntity;
import com.cloud.shoppingcart.cart.repository.ShoppingCartRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.shoppingcart.product.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShoppingService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ProductService productService;
    
    private final ShoppingCartRepository shoppingCartRepository;

    @Autowired
    public ShoppingService(ProductService productService, ShoppingCartRepository shoppingCartRepository) {
        this.productService = productService;
        this.shoppingCartRepository = shoppingCartRepository;
    }

    public Iterable<ShoppingCartEntity> getAll(){
        return shoppingCartRepository.findAll();
    }
    public Optional<ShoppingCartEntity> getCart(String customerId){
        logger.info("ShoppingService.Controller.cartForUser"+customerId);
        return shoppingCartRepository.findByCustomerId(customerId);

    }
    public Optional<ShoppingCartEntity> addToCart(String customerId, CartItemEntity item){
        return shoppingCartRepository.findByCustomerId(customerId).map(c ->{
            item.setId(UUID.randomUUID());
            item.setCart(c);
            c.getItems().add(item);
            return c;
        });

    }
}
