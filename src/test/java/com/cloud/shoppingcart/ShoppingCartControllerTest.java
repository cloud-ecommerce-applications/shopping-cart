package com.cloud.shoppingcart;

import com.cloud.shoppingcart.cart.controller.ShoppingCartController;
import com.cloud.shoppingcart.cart.service.ShoppingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cloud.shoppingcart.product.ProductService;

public class ShoppingCartControllerTest  extends MockitoExtension{
	
	@Mock
	ShoppingService shoppingService;
	
	@Mock
	ProductService productService;
	
	ShoppingCartController shoppingCartController;
	
	

	  @BeforeEach
	  void initUseCase() {
		  shoppingCartController = new ShoppingCartController(shoppingService, productService); 
	  }

	  @Test
	  void savedUserHasRegistrationDate() {
	    // ...
	  }

}
