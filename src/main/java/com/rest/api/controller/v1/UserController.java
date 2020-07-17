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
@RequiredArgsConstructor // class ��ܿ� �����ؼ� final ����� ��ü�� ������ ������ �Ѵ�. ������� �ʰ� autowired�� ����ص� ����.
@RestController // ������� json���� ǥ��
@RequestMapping(value = "/v1")
public class UserController {
	
	private final UserJpaRepo userJpaRepo;
	
	@ApiOperation(value = "ȸ�� ��ȸ", notes = "���ȸ���� ��ȸ�Ѵ�.")
	@GetMapping(value = "/user")
	public List<User> findAllUser(){
		return userJpaRepo.findAll();
	}
	
	@ApiOperation(value = "ȸ�� �Է�", notes = "ȸ���� �Է��Ѵ�.")
	@PostMapping(value = "/user")
	public User save(@ApiParam(value = "ȸ�� ���̵�", required = true) @RequestParam String uid
			, @ApiParam(value = "ȸ���̸�", required = true) @RequestParam String name) {
		User user = User.builder()
				.uid(uid)
				.name(name)
				.build();
		return userJpaRepo.save(user);
	}
}
