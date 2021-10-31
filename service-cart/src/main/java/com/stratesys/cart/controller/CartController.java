package com.stratesys.cart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stratesys.cart.dtos.CartDto;
import com.stratesys.cart.services.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	@Qualifier("serviceFeign")
	private CartService cartService;
	
	@GetMapping("/all")
	public List<CartDto> findAll(){
		return cartService.findAll();
	}
	
	@GetMapping("/by/{id}/amount/{amount}")
	public CartDto findById(@PathVariable Long id, @PathVariable Integer amount) {
		return cartService.findById(id, amount);
	}
}
