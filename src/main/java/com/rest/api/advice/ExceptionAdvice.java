package com.rest.api.advice;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.rest.api.advice.exception.CUserNotFoundException;
import com.rest.api.model.response.CommonResult;
import com.rest.api.service.ResponseService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestControllerAdvice // json형태로 반환, 기본은 ControllerAdvice, 특정패키지 하위의 controller에 적용하려면 (basePackages = "com.rest.api")
public class ExceptionAdvice {
	
	private final ResponseService responseService;
	
//	@ExceptionHandler(Exception.class) // 해당 exception처리 명시
//	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 오류발생시 500으로 리턴
//	protected CommonResult defualtException(HttpServletRequest request, Exception e) {
//		return responseService.getFailResult();
//	}
	
	@ExceptionHandler(CUserNotFoundException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	protected CommonResult userNotFoundException(HttpServletRequest request, CUserNotFoundException e) {
		return responseService.getFailResult();
	}
	
}
