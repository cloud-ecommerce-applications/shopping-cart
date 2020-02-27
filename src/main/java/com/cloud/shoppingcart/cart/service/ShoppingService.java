package com.cloud.shoppingcart.cart.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.cloud.shoppingcart.cart.model.CartItemEntity;
import com.cloud.shoppingcart.cart.model.ShoppingCartEntity;
import com.cloud.shoppingcart.cart.repository.ShoppingCartRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.shoppingcart.product.ProductService;

import lombok.RequiredArgsConstructor;

import javax.swing.text.html.Option;

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

    public Set<ShoppingCartEntity> getAll(){
        return shoppingCartRepository.findAll();
    }
    public Optional<ShoppingCartEntity> getCart(String userName){
        logger.info("ShoppingService.Controller.cartForUser"+userName);
        Optional<ShoppingCartEntity> cart= shoppingCartRepository.findAll().stream().filter(c -> c.getCustomerId().equalsIgnoreCase(userName)).findFirst();
        cart.ifPresent(c ->{
            c.getItems().stream().forEach( i->{
                i.setUnitCost(productService.getUnitCost(i.getSku()));
            });
        });
        return cart;
    }
    public Optional<ShoppingCartEntity> addToCart(String userName, CartItemEntity item){
    	return shoppingCartRepository.findAll().stream().filter(c -> c.getCustomerId().equalsIgnoreCase(userName))
                .findFirst().map(c -> {
                        item.setId(c.getItems().size());
                         c.getItems().add(item);
                    return c;

        });

    }
}
