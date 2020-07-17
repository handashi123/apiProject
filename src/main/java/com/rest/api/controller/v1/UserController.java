package com.rest.api.controller.v1;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rest.api.UserJpaRepo;
import com.rest.api.entity.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@Api(tags = {"1. User"})
@RequiredArgsConstructor // class 상단에 선언해서 final 선언된 객체에 생성자 주입을 한다. 사용하지 않고 autowired를 사용해되 가능.
@RestController // 결과값을 json으로 표시
@RequestMapping(value = "/v1")
public class UserController {
	
	private final UserJpaRepo userJpaRepo;
	
	@ApiOperation(value = "회원 조회", notes = "모든회원을 조회한다.")
	@GetMapping(value = "/user")
	public List<User> findAllUser(){
		return userJpaRepo.findAll();
	}
	
	@ApiOperation(value = "회원 입력", notes = "회원을 입력한다.")
	@PostMapping(value = "/user")
	public User save(@ApiParam(value = "회원 아이디", required = true) @RequestParam String uid
			, @ApiParam(value = "회원이름", required = true) @RequestParam String name) {
		User user = User.builder()
				.uid(uid)
				.name(name)
				.build();
		return userJpaRepo.save(user);
	}
}
