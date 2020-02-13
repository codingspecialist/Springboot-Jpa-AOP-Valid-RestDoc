package com.cos.bindingresult.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.bindingresult.dto.ResponseCM;
import com.cos.bindingresult.model.Product;
import com.cos.bindingresult.repository.ProductRepository;

/**
 * 
 * @author it
 *  Logger File 남기고
 *  모든 메소드마다 자동화
 *
 */

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

//		product.setId(id);
//		productRepository.save(product);
		
		Product persistenceProduct = productRepository.findById(id).get();
		
		persistenceProduct.setName(product.getName());
		persistenceProduct.setPrice(product.getPrice());
		persistenceProduct.setOrdering(product.getOrdering());
		
		return new ResponseEntity<ResponseCM>(new ResponseCM(200, "ok"),HttpStatus.OK);
	}

}



