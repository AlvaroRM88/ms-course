package com.stratesys.java8examples.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lambda/example")
public class LambdaController {
	
	// First example, Supplier
	// No recibe nada, pero devuelve un resultado
	@GetMapping("/fiveRandoms")
	public ResponseEntity<?> generateFiveRandoms() {
		
		List<Integer> randoms = new ArrayList<>();
		for(int i = 0; i < 5; i++) {
			Integer random = new Random().nextInt();
			randoms.add(random);
		}
		
		return new ResponseEntity<>(randoms, HttpStatus.OK);
	}
	

	@GetMapping("/supplier")
	public ResponseEntity<?> lambdaExampleSupplier() {
		
		List<Integer> fiveRandoms = Stream.generate( () -> new Random().nextInt() )
			.limit(5)
			.collect(Collectors.toList());
		
		return ResponseEntity.ok(fiveRandoms);
		
	}
	
	// Second example, Consumer and Biconsumer
	// Recibe una variable generica, hace uso de ella pero no devuelve nada
	@GetMapping("/consumer")
	public void lambdaExampleConsumer() {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
		numbers.forEach(number -> System.out.println(number));
		
	}
	
	@GetMapping("/biconsumer")
	public void lambdaExampleBiconsumer() {
		BiConsumer<Integer, Integer> addTwo = (x, y) -> System.out.println(x + y);
		addTwo.accept(1, 2);
	}
	
	// Third example, Predicate 
	// Recive un argumento y devuelve un boolean, para evaluar hipotesis
	@GetMapping("/predicate")
	public void lambdaExamplePredicate() {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
		
		numbers.stream()
			.filter(number -> number % 2 == 0)
			.forEach(System.out::println);
	}
	
	// Four example, Función  
	// Recive  y devuelve un argumento.
	@GetMapping("/function")
	public ResponseEntity<?> lambdaExampleFunction() {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
		
		List<Double> numbersAsDouble = numbers.stream()
			.map(number -> number.doubleValue())
			.collect(Collectors.toList());
		
		return ResponseEntity.ok(numbersAsDouble);
	}
	
	
	
	

}
