package com.rest.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.Getter;
import lombok.Setter;

@Controller
public class HelloController {
	/**
	 * ȭ�鿡 helloworld�� ���
	 * @GetMapping �� GET ��û mapping ������ RequestMapping�� ���̾�
	 * @ResponseBody�� ȭ�鿡 �״�� return�� ǥ��
	 * @return
	 */
	@GetMapping(value = "/helloworld/string")
	@ResponseBody
	public String helloworldString() {
		return "helloworld";
	}
	/**
	 * vo��ü�� json�������� �������
	 * @return
	 */
	@GetMapping(value = "/helloworld/json")
	@ResponseBody
	public Hello helloworldJson() {
		Hello hello = new Hello();
		hello.message = "helloworld";
		return hello;
	}
	/**
	 * ȭ�鿡 helloworld.ftl�� ������ ���
	 * @return
	 */
	@GetMapping(value = "/helloworld/page")
	public String helloworld() {
		return "helloworld";
	}
	
	@Setter
	@Getter
	public static class Hello {
		private String message;
	}
}
