package com.stratesys.cart.services;

import java.util.List;

import com.stratesys.cart.dtos.CartDto;

public interface CartService {
	
	public List<CartDto> findAll();
	public CartDto findById(Long id, Integer amount);

}
