package com.rest.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rest.api.model.response.CommonResult;
import com.rest.api.model.response.ListResult;
import com.rest.api.model.response.SingleResult;

@Service // �ش� Class �� Service���� ���
public class ResponseService {
	
	// enum���� api ��û ����� ���� code, message�� �����մϴ�.
	public enum CommonResponse {
		SUCCESS(0, "�����Ͽ����ϴ�."),
		FAIL(-1, "�����Ͽ����ϴ�.");
		
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
	
	// ��� �𵨿� api ��û ���� �����͸� �������ִ� �޼ҵ�
	private void setSucessResult(CommonResult result) {
		result.setSuccess(true);
		result.setCode(CommonResponse.SUCCESS.getCode());
		result.setMsg(CommonResponse.SUCCESS.getMsg());
	}
	
	// ���� ����� ó���ϴ� �޼ҵ�
	public CommonResult getSuccessResult() {
		CommonResult result = new CommonResult();
		setSucessResult(result);
		return result;
	}
	
	// ���� ����� ó���ϴ� �޼ҵ�
	public CommonResult getFailResult() {
		CommonResult result = new CommonResult();
		result.setSuccess(false);
		result.setCode(CommonResponse.FAIL.getCode());
		result.setMsg(CommonResponse.FAIL.getMsg());
		return result;
	}
	
	// ���ϰ� ����� ó���ϴ� �޼ҵ�
	public <T> SingleResult<T> getSingleResult(T data) {
		SingleResult<T> result = new SingleResult<>();
		result.setData(data);
		setSucessResult(result); // ��������� ����
		return result;
		
	}
	
	// ���߰� ����� ó���ϴ� �޼ҵ�
	public <T> ListResult<T> getListResult(List<T> list) {
		ListResult<T> result = new ListResult<>();
		result.setList(list);
		setSucessResult(result); // ��������� ����
		return result;
	}
	
}
