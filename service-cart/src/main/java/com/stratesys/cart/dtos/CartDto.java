package com.stratesys.cart.dtos;

public class CartDto {
	
	private ProductDto productDto;
	private Integer amount;

	public CartDto() {
	}

	public CartDto(ProductDto productDto, Integer amount) {
		this.productDto = productDto;
		this.amount = amount;
	}

	public ProductDto getProductDto() {
		return productDto;
	}

	public void setProductDto(ProductDto productDto) {
		this.productDto = productDto;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	public Double getTotal() {
		return productDto.getPrecio() * amount.doubleValue();
	}

}
