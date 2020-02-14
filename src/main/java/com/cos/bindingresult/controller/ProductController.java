package com.cos.bindingresult.controller;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.bindingresult.dto.ResponseCM;
import com.cos.bindingresult.model.Product;
import com.cos.bindingresult.repository.ProductRepository;



/**
 * @author cos
 *  Logger File 남기고
 *  모든 메소드마다 자동화
 *  Maven+JUnit5+RestDoc로 adoc만들기
 **/

@RestController
public class ProductController {

	@Autowired
	private ProductRepository productRepository;
	
	// @RequestBody -> BufferedReader
	@PostMapping("/api/product")
	public ResponseEntity<?> 상품등록(@Valid @RequestBody Product product, 
			BindingResult bindingResult) {
			
		productRepository.save(product);
		
		return new ResponseEntity<ResponseCM>(new ResponseCM(200, "ok"),HttpStatus.OK);	
	}
	
	@Transactional
	@PutMapping("/api/product/{id}")
	public ResponseEntity<?> 상품수정(@PathVariable int id,
			@Valid @RequestBody Product product, BindingResult bindingResult){

		Product persistenceProduct = productRepository.findById(id).get();
		
		persistenceProduct.setName(product.getName());
		persistenceProduct.setPrice(product.getPrice());
		persistenceProduct.setOrdering(product.getOrdering());
		
		return new ResponseEntity<ResponseCM>(new ResponseCM(200, "ok"),HttpStatus.OK);
	}
	
	@GetMapping("/api/product")
	public ResponseEntity<?> 상품전체조회(){
		List<Product> products = productRepository.findAll();
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	
	@GetMapping("/api/product/{id}")
	public ResponseEntity<?> 상품상세조회(@PathVariable int id){
		Product product = productRepository.findById(id).get();
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

}



