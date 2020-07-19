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
@RestControllerAdvice // json���·� ��ȯ, �⺻�� ControllerAdvice, Ư����Ű�� ������ controller�� �����Ϸ��� (basePackages = "com.rest.api")
public class ExceptionAdvice {
	
	private final ResponseService responseService;
	
//	@ExceptionHandler(Exception.class) // �ش� exceptionó�� ���
//	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // �����߻��� 500���� ����
//	protected CommonResult defualtException(HttpServletRequest request, Exception e) {
//		return responseService.getFailResult();
//	}
	
	@ExceptionHandler(CUserNotFoundException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	protected CommonResult userNotFoundException(HttpServletRequest request, CUserNotFoundException e) {
		return responseService.getFailResult();
	}
	
}
