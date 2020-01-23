package com.cloud.shoppingcart;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartRepository {
	
	 private static Set<ShoppingCart> carts= new HashSet<ShoppingCart>();
	    static {
	        Set<CartItem> user1Items = new HashSet<>();
	        Set<CartItem> user2Items = new HashSet<>();
	        ShoppingCart user1Cart = new ShoppingCart();
	        ShoppingCart user2Cart = new ShoppingCart();
	        for(int i=1; i<=5;i++){
	            CartItem item = new CartItem();
	            item.setId(1);
	            item.setSku("sku"+i);
	            //Get unit cost from product service
	            //i.setUnitCost();
	           
	            user1Items.add(item);
	            user2Items.add(item);
	        }
	        user1Cart.setCustomerId("user1");
	        user1Cart.setItems(user1Items);
	        user2Cart.setCustomerId("user2");
	        user2Cart.setItems(user2Items);
	        carts.add(user1Cart);
	        carts.add(user2Cart);
	    }
	
	public Set<ShoppingCart> findAll(){
		return carts;
	}

}
