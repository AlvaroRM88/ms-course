package com.stratesys.product.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stratesys.product.models.entity.Product;
import com.stratesys.product.services.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Value("${server.port}")
	private Integer port;
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/all")
	public List<Product> findAll(){
		return productService.findAll().stream().map(product -> {
			product.setPort(port);
			return product;
		}).collect(Collectors.toList());
	}
	
	@GetMapping("/by/{id}")
	public Product findById(@PathVariable Long id) {
		Product product = productService.findById(id);
		if(product != null) {
			product.setPort(port);
		}
		return product;
	}

}
