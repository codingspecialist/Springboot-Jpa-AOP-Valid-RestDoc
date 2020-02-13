package com.cos.bindingresult.aop.warn;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Component
@Aspect
public class BindingAdvice {

	private static final Logger log = LoggerFactory.getLogger(BindingAdvice.class);

	// * return Type
	// * Controller 타겟
	// *(..) 모든 메소드의 모든 인자허용
	// @Before(직전), @After(직후), @Around(직전,직후,예외상황)
	@Around("execution(* com.cos.bindingresult.controller..*Controller.*(..))")
	public Object bindingPrint(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		
		String type = proceedingJoinPoint.getSignature().getDeclaringTypeName();
		String method = proceedingJoinPoint.getSignature().getName();
		
		Object[] args = proceedingJoinPoint.getArgs();
		for(Object arg: args) {
			if(arg instanceof BindingResult) {
				BindingResult bindingResult = (BindingResult) arg;
				
				if(bindingResult.hasErrors()) {
					Map<String, String> errorMap = new HashMap<>();
					
					for(FieldError error : bindingResult.getFieldErrors()) {
						log.warn(type+"."+method+"() => 필드 : "+error.getField()+" 메시지 : "+error.getDefaultMessage());
						errorMap.put(error.getField(), error.getDefaultMessage());
					}
					
					return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
				}	
			}
		}
		return proceedingJoinPoint.proceed();
	}
}
