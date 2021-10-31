package com.stratesys.cart.dtos;

import java.util.Date;
import java.util.Objects;

public class ProductDto {
	
	private Long id;
	private String name;
	private Double price;
	private Date createAt;
	private Integer port;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return name;
	}
	public void setNombre(String nombre) {
		this.name = nombre;
	}
	public Double getPrecio() {
		return price;
	}
	public void setPrecio(Double precio) {
		this.price = precio;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	@Override
	public int hashCode() {
		return Objects.hash(createAt, id, name, port, price);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductDto other = (ProductDto) obj;
		return Objects.equals(createAt, other.createAt) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name) && Objects.equals(port, other.port)
				&& Objects.equals(price, other.price);
	}
	
	@Override
	public String toString() {
		return "ProductDto [id=" + id + ", name=" + name + ", price=" + price + ", createAt=" + createAt + ", port="
				+ port + "]";
	}
	
}
