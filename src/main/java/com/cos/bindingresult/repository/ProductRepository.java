package com.cos.bindingresult.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cos.bindingresult.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}
