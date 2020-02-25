package com.cloud.shoppingcart.cart.repository;

import java.util.HashSet;
import java.util.Set;

import com.cloud.shoppingcart.cart.model.CartItemEntity;
import com.cloud.shoppingcart.cart.model.ShoppingCartEntity;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartRepository {
	
	 private static Set<ShoppingCartEntity> carts= new HashSet<ShoppingCartEntity>();
	    static {
	        Set<CartItemEntity> user1Items = new HashSet<>();
	        Set<CartItemEntity> user2Items = new HashSet<>();
	        ShoppingCartEntity user1Cart = new ShoppingCartEntity();
	        ShoppingCartEntity user2Cart = new ShoppingCartEntity();
	        for(int i=1; i<=5;i++){
	            CartItemEntity item = new CartItemEntity();
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
	
	public Set<ShoppingCartEntity> findAll(){
		return carts;
	}

}
