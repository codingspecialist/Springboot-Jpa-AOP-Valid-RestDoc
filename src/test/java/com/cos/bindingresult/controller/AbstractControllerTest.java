package com.cos.bindingresult.controller;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;

import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

// JUnit 5 - 테스트 참고 문헌
// https://seungwoo0429.tistory.com/34
// https://github.com/KingCjy/blog/blob/master/Spring/Spring-rest-docs/src/test/java/com/kingcjy/main/controller/ProductControllerTest.java

@ExtendWith({ RestDocumentationExtension.class, SpringExtension.class })
@SpringBootTest
public abstract class AbstractControllerTest {

	protected MockMvc mvc;

	protected RestDocumentationResultHandler document;

	@BeforeEach
	private void setup(WebApplicationContext webApplicationContext,
			RestDocumentationContextProvider restDocumentation) {
		this.document = document("{class-name}/{method-name}",
				Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
				Preprocessors.preprocessResponse(Preprocessors.prettyPrint()));

		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.addFilter(new CharacterEncodingFilter(StandardCharsets.UTF_8.name(), true))
				.apply(documentationConfiguration(restDocumentation))		
				.alwaysDo(document)
				.build();
	}
}
