package com.rest.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rest.api.model.response.CommonResult;
import com.rest.api.model.response.ListResult;
import com.rest.api.model.response.SingleResult;

@Service // 해당 Class 가 Service임을 명시
public class ResponseService {
	
	// enum으로 api 요청 경과에 대한 code, message를 정의합니다.
	public enum CommonResponse {
		SUCCESS(0, "성공하였습니다."),
		FAIL(-1, "실패하였습니다.");
		
		int code;
		String msg;
		
		CommonResponse(int code, String msg) {
			this.code = code;
			this.msg = msg;
		}
		
		public int getCode() {
			return code;
		}
		public String getMsg() {
			return msg;
		}
	}
	
	// 결과 모델에 api 요청 성공 데이터를 세팅해주는 메소드
	private void setSucessResult(CommonResult result) {
		result.setSuccess(true);
		result.setCode(CommonResponse.SUCCESS.getCode());
		result.setMsg(CommonResponse.SUCCESS.getMsg());
	}
	
	// 성공 결과만 처리하는 메소드
	public CommonResult getSuccessResult() {
		CommonResult result = new CommonResult();
		setSucessResult(result);
		return result;
	}
	
	// 실패 결과만 처리하는 메소드
	public CommonResult getFailResult() {
		CommonResult result = new CommonResult();
		result.setSuccess(false);
		result.setCode(CommonResponse.FAIL.getCode());
		result.setMsg(CommonResponse.FAIL.getMsg());
		return result;
	}
	
	// 단일건 결과를 처리하는 메소드
	public <T> SingleResult<T> getSingleResult(T data) {
		SingleResult<T> result = new SingleResult<>();
		result.setData(data);
		setSucessResult(result); // 성공결과를 세팅
		return result;
		
	}
	
	// 다중건 결과를 처리하는 메소드
	public <T> ListResult<T> getListResult(List<T> list) {
		ListResult<T> result = new ListResult<>();
		result.setList(list);
		setSucessResult(result); // 성공결과를 세팅
		return result;
	}
	
}
