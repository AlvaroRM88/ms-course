package com.stratesys.cart.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.stratesys.cart.dtos.CartDto;
import com.stratesys.cart.dtos.ProductDto;
import com.stratesys.cart.services.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {
	
	private static Logger log = LoggerFactory.getLogger(CartController.class);

	@Value("${configuracion.prueba}")
	private String test;
	
	@Autowired
	private Environment environment;

	@Autowired
	@Qualifier("serviceFeign")
	private CartService cartService;
	
	@GetMapping("/all")
	public List<CartDto> findAll(){
		return cartService.findAll();
	}
	
	@HystrixCommand(fallbackMethod="findByIdError")
	@GetMapping("/by/{id}/amount/{amount}")
	public CartDto findById(@PathVariable Long id, @PathVariable Integer amount) {
		return cartService.findById(id, amount);
	}
	
	public CartDto findByIdError(Long id, Integer amount) {
		ProductDto product = new ProductDto();
		product.setNombre("Producto Hystrix");
		product.setPrecio(1D);
		CartDto cart = new CartDto();
		cart.setAmount(0);
		cart.setProductDto(product);
		return cart;
	}
	
	
	@GetMapping("/config")
	public ResponseEntity<?> getConfig(@Value("${server.port}") String port){
		
		log.info(test);
		
		Map<String, String> json = new HashMap<>();
		json.put("config_test", test);
		json.put("port", port);
		
		if(environment.getActiveProfiles().length>0 && environment.getActiveProfiles()[0].equals("dev")) {
			json.put("autor.nombre", environment.getProperty("configuracion.autor.nombre"));
		}

		
		return new ResponseEntity<Map<String, String>>(json, HttpStatus.OK);
	}

}
