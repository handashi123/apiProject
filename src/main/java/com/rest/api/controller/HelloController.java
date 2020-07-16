package com.rest.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.Getter;
import lombok.Setter;

@Controller
public class HelloController {
	/**
	 * 화면에 helloworld가 출력
	 * @GetMapping 은 GET 요청 mapping 보통은 RequestMapping을 많이씀
	 * @ResponseBody는 화면에 그대로 return값 표시
	 * @return
	 */
	@GetMapping(value = "/helloworld/string")
	@ResponseBody
	public String helloworldString() {
		return "helloworld";
	}
	/**
	 * vo객체를 json형식으로 출력해줌
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
	 * 화면에 helloworld.ftl의 내용이 출력
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
