package com.cloud.shoppingcart;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cloud.shoppingcart.product.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShoppingService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ProductService productService;
    
    private final ShoppingCartRepository shoppingCartRepository;

    public Set<ShoppingCart> getAll(){
        return shoppingCartRepository.findAll();
    }
    public Optional<ShoppingCart> forUser(String user){
        logger.info("ShoppingService.Controller.cartForUser"+user);
        Optional<ShoppingCart> cart= shoppingCartRepository.findAll().stream().filter(c -> c.getCustomerId().equalsIgnoreCase(user)).findFirst();
        cart.ifPresent(c ->{
            c.getItems().stream().forEach( i->{
                i.setUnitCost(productService.getUnitCost(i.getSku()));
            });
        });
        return cart;
    }
    public Set<CartItem> addToCart(String userName, CartItem item){
    	return shoppingCartRepository.findAll().stream().filter(c -> c.getCustomerId().equalsIgnoreCase(userName))
                .findFirst().map(c -> {
                        item.setId(c.getItems().size());
                         c.getItems().add(item);
                      return c.getItems();

        }).orElse(new HashSet<CartItem>());

    }
}
