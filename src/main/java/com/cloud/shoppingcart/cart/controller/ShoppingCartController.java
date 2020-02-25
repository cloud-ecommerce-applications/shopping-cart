package com.cloud.shoppingcart.cart.controller;


import java.util.Set;
import java.util.stream.Collectors;

import com.cloud.shoppingcart.cart.dto.ShoppingCartDTO;
import com.cloud.shoppingcart.cart.service.ShoppingService;
import com.cloud.shoppingcart.cart.model.ShoppingCartEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.shoppingcart.product.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ShoppingCartController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ShoppingService service;

    private final ProductService productService;

    @Autowired
    public ShoppingCartController(ShoppingService service, ProductService productService) {
        this.service = service;
        this.productService = productService;
    }

    @GetMapping("/")
    public String home(){
        return "shopping home";
    }

    @GetMapping("/carts")
    public Set<ShoppingCartDTO> allCarts(){
        return service.getAll().stream()
                .map(ShoppingCartEntity::toDto).collect(Collectors.toSet());
    }



}
