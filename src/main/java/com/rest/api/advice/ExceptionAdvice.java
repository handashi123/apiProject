package com.rest.api.advice;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
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
	private final MessageSource messageSource;
	
	@ExceptionHandler(Exception.class) // 해당 exception처리 명시
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 오류발생시 500으로 리턴
	protected CommonResult defualtException(HttpServletRequest request, Exception e) {
		// 예외 처리의 메시지를 MessageSource에서 가져오도록 수정
		return responseService.getFailResult(Integer.valueOf(getMessage("unKnown.code")), getMessage("unKnown.msg"));
	}
	
	@ExceptionHandler(CUserNotFoundException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	protected CommonResult userNotFoundException(HttpServletRequest request, CUserNotFoundException e) {
		// 예외 처리의 메시지를 MessageSource에서 가져오도록 수정
		return responseService.getFailResult(Integer.valueOf(getMessage("userNotFound.code")), getMessage("userNotFound.msg"));
	}
	
	// code정보에 해당하는 메시지를 조회한다.
	private String getMessage(String code) {
		return getMessage(code, null);
	}
	
	// code정보, 추가 argument로 현재 locale에 맞는 메시지를 조회한다.
	private String getMessage(String code, Object[] args) {
		System.out.println("LocaleContextHolder.getLocale() : " + LocaleContextHolder.getLocale());
		return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
	}
	
}
