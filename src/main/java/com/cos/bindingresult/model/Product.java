package com.cos.bindingresult.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	// @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "상품명에 한글이 입력될 수 없습니다.")
	@Size(max=10, message="상품명의 길이가 초과되었습니다.")
	@NotBlank(message="상품명을 입력하세요.")
	private String name;
	
	@NotNull(message = "가격을 입력하세요.")
	private Integer price;
	
	@NotNull(message = "주문수량을 입력하세요.")
	private Integer ordering;
}
