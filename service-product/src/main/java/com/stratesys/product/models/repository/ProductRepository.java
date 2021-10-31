package com.stratesys.product.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.stratesys.product.models.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
