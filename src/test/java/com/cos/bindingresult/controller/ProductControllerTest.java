package com.cos.bindingresult.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import com.cos.bindingresult.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureRestDocs(uriScheme = "http", uriHost = "localhost", uriPort = 8080)
public class ProductControllerTest extends AbstractControllerTest {

	private ObjectMapper mapper;

	@Test
	public void 상품등록() throws Exception {

		Product entity = new Product();
		entity.setName("세탁기");
		entity.setPrice(100000);
		entity.setOrdering(1);

		ObjectMapper mapper = new ObjectMapper();

		mvc.perform(post("/api/product")
				.content(mapper.writeValueAsString(entity))
				
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andDo(document);

	}

	@Test
	public void 상품수정() throws Exception {
		Product entity = new Product();
		entity.setName("냉장고");
		entity.setPrice(2000000);
		entity.setOrdering(2);

		ObjectMapper mapper = new ObjectMapper();

		mvc.perform(put("/api/product/{id}", 1)
                .content(mapper.writeValueAsString(entity))
                
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document.document(
                        requestFields(
                        		fieldWithPath("id").description("상품번호"),
                                fieldWithPath("name").description("상품명"),
                                fieldWithPath("price").type(Integer.class).description("상품가격"),
                                fieldWithPath("ordering").type(Integer.class).description("상품주문수")
                        )
                ));

	}
	
	@Test
	public void 상품전체조회() throws Exception {
		mvc.perform(
                get("/api/product")
//                        .param("page", "1")
//                        .param("size", "10")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document.document(
//                        requestParameters(
//                                parameterWithName("page").description("페이지 번호"),
//                                parameterWithName("size").description("페이지 사이즈")
//                        ),
                        responseFields(
                                fieldWithPath("[].id").description("상품번호"),
                                fieldWithPath("[].name").description("상품명"),
                                fieldWithPath("[].price").type(Integer.class).description("상품가격"),
                                fieldWithPath("[].ordering").type(Integer.class).description("상품주문수")
                        )
                ))
                .andExpect(jsonPath("[0].id", is(notNullValue())))
                .andExpect(jsonPath("[0].name", is(notNullValue())))
                .andExpect(jsonPath("[0].price", is(notNullValue())))
                .andExpect(jsonPath("[0].ordering", is(notNullValue())));
	}

	@Test
	public void 상품상세조회() throws Exception {
		mvc.perform(
                get("/api/product/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document.document(
                        responseFields(
                                fieldWithPath("id").description("상품번호"),
                                fieldWithPath("name").description("상품명"),
                                fieldWithPath("price").type(Integer.class).description("상품가격"),
                                fieldWithPath("ordering").type(Integer.class).description("상품주문수")
                        )
                ))
                .andExpect(jsonPath("id", is(notNullValue())))
                .andExpect(jsonPath("name", is(notNullValue())))
                .andExpect(jsonPath("price", is(notNullValue())))
                .andExpect(jsonPath("ordering", is(notNullValue())));
	}
}
