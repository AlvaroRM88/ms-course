package com.stratesys.product.services;

import java.util.List;

import com.stratesys.product.models.entity.Product;

public interface ProductService {

	public List<Product> findAll();
	public Product findById(Long id);
	
}
