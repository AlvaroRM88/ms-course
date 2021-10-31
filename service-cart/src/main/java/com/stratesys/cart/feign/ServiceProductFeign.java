package com.stratesys.cart.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.stratesys.cart.dtos.ProductDto;

@FeignClient(name = "service-product", url="localhost:8001/product")
public interface ServiceProductFeign {
	
	@GetMapping("/all")
	public List<ProductDto> findAll();
	
	@GetMapping("/by/{id}")
	public ProductDto findById(@PathVariable Long id);

}
