package com.stratesys.cart.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.stratesys.cart.dtos.CartDto;
import com.stratesys.cart.feign.ServiceProductFeign;

@Service("serviceFeign")
@Primary
public class CartServiceFeignImpl implements CartService {

	@Autowired
	private ServiceProductFeign serviceProductFeign;

	@Override
	public List<CartDto> findAll() {
		return serviceProductFeign.findAll().stream().map(p -> new CartDto(p, 1)).collect(Collectors.toList());
	}

	@Override
	public CartDto findById(Long id, Integer amount) {
		return new CartDto(serviceProductFeign.findById(id), amount);
	}
	
}
