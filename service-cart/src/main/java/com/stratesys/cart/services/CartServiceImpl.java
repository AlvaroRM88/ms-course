package com.stratesys.cart.services;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.stratesys.cart.dtos.CartDto;
import com.stratesys.cart.dtos.ProductDto;

@Service("serviceRestTemplate")
public class CartServiceImpl implements CartService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public List<CartDto> findAll() {
		List<ProductDto> products = Arrays.asList(restTemplate.getForObject("http://service-product/product/all", ProductDto[].class));
		
		return products.stream().map(product -> new CartDto(product, 1)).collect(Collectors.toList());
	}

	@Override
	public CartDto findById(Long id, Integer amount) {
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("id", id.toString());
		ProductDto product = restTemplate.getForObject("http://service-product/product/by/{id}", ProductDto.class, pathVariables);
		return new CartDto(product, amount);
	}

}
