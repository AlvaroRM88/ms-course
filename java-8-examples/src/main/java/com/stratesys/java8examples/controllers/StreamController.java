package com.stratesys.java8examples.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stream/example")
public class StreamController {
	
	@PostMapping("/calculateSimpleTax/{taxPercent}")
	public ResponseEntity<?> calculeSimpleTax(@PathVariable("taxPercent") Integer taxPercent, @RequestBody List<Integer> prices) {
		

		List<Double> pricesPlusTaxes = prices.stream()
			.map(price -> {
				Double percent = Double.valueOf(taxPercent * 0.01);
				return price * percent;
			}
		).collect(Collectors.toList());
			
		return ResponseEntity.ok(pricesPlusTaxes);
	}
	
	@PostMapping("/calculePriceWithSimpleTax/{taxPercent}")
	public ResponseEntity<?> calculePriceWithSimpleTax(@PathVariable("taxPercent") Integer taxPercent, @RequestBody List<Integer> prices) {
		

		List<Double> pricesPlusTaxes = prices.stream()
			.map(price -> {
				Double percent = Double.valueOf(taxPercent * 0.01);
				return price + (price * percent);
			}
		).collect(Collectors.toList());
			
		return ResponseEntity.ok(pricesPlusTaxes);
	}
	
	@PostMapping("/amountPricesBiggerThanOneHundred/{taxPercent}")
	public ResponseEntity<?> getTotalPricesGreaterThanOneHundred(@PathVariable("taxPercent") Integer taxPercent, @RequestBody List<Integer> prices) {
		

		long pricesPlusTaxes = prices.stream()
			.map(price -> {
				Double percent = Double.valueOf(taxPercent * 0.01);
				return price + (price * percent);
			})
			.filter(price -> price > 100)
			.count();
			
		return ResponseEntity.ok(pricesPlusTaxes);
	}
	
	//Ejemplo con depende del precio un porcentage
	
	
	
	@PostMapping("/existProduct/{productToSearch}")
	public ResponseEntity<?> existProduct(@PathVariable("productToSearch") String productToSearch, @RequestBody List<String> prodcuts) {
		
		Boolean existProduct = prodcuts.stream()
			.anyMatch(product -> {
				return product.toLowerCase().equals(productToSearch.toLowerCase());
			});
		
		return ResponseEntity.ok(existProduct);
	}
	
	@PostMapping("/getExistProduct/{productToSearch}")
	public ResponseEntity<?> getExistProduct(@PathVariable("productToSearch") String productToSearch, @RequestBody List<String> prodcuts) {
		
		String existProduct = prodcuts.stream()
			.filter(product -> product.toLowerCase().equals(productToSearch.toLowerCase()))
			.findFirst()
			.orElse("No find");
		
		return ResponseEntity.ok(existProduct);
	}
	
	

}
