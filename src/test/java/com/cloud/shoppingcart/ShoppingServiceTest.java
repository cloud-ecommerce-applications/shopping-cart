package com.cloud.shoppingcart;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import com.cloud.shoppingcart.product.ProductService;

@ExtendWith(MockitoExtension.class)
public class ShoppingServiceTest {

	@Mock
	ProductService productService;
	
	@Mock
	ShoppingCartRepository shoppingCartRepository;
	
	ShoppingService shoppingService;
	
	 @BeforeEach
	  void initUseCase() {
		 shoppingService = new ShoppingService(productService, shoppingCartRepository); 
	  }

	  @Test
	  void fiddAllReturnsData() {
	    when(shoppingCartRepository.findAll()).thenReturn(cart());
	    assertTrue(!shoppingService.getAll().isEmpty());
	  }
	  
	  @Test
	  void addToCartSavesTheData() {
	    when(shoppingCartRepository.findAll()).thenReturn(cart());
	    CartItem item = new CartItem();
        item.setId(10);
        item.setSku("sku3");
        assertTrue(shoppingService.addToCart("user1", item).size()==6);
	  }
	  
	  private Set<ShoppingCart> cart(){
		  Set<ShoppingCart> carts= new HashSet<ShoppingCart>();
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
		  return carts;
	  }
	  
	  
}
