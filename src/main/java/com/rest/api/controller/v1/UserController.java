package com.rest.api.controller.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rest.api.UserJpaRepo;
import com.rest.api.entity.User;
import com.rest.api.model.response.CommonResult;
import com.rest.api.model.response.ListResult;
import com.rest.api.model.response.SingleResult;
import com.rest.api.service.ResponseService;

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
	private final ResponseService responseService; // ����� ó���� service
	
	@ApiOperation(value = "ȸ�� ����Ʈ ��ȸ", notes = "���ȸ���� ��ȸ�Ѵ�.")
	@GetMapping(value = "/users")
	public ListResult<User> findAllUser(){
		// ��� �����Ͱ� �������� ��� getListResult�� �̿��ؼ� ����� ����Ѵ�.		
		return responseService.getListResult(userJpaRepo.findAll());
	}
	
	@ApiOperation(value = "ȸ�� �ܰ� ��ȸ", notes = "userId�� ȸ���� ��ȸ�Ѵ�.")
	@GetMapping(value = "/user/{msrl}")
	public SingleResult<User> findUserById(
			@ApiParam(value = "ȸ��ID", required = true) @PathVariable long msrl
			){
		// ��������Ͱ� ���ϰ��ΰ�� getSingleResult�� �̿��ؼ� ����� ����Ѵ�.
		return responseService.getSingleResult(userJpaRepo.findById(msrl).orElse(null));
	}
	
	@ApiOperation(value = "ȸ�� �Է�", notes = "ȸ���� �Է��Ѵ�.")
	@PostMapping(value = "/user")
	public SingleResult<User> save(
			@ApiParam(value = "ȸ�� ���̵�", required = true) @RequestParam String uid
			, @ApiParam(value = "ȸ���̸�", required = true) @RequestParam String name
			) {
		User user = User.builder()
				.uid(uid)
				.name(name)
				.build();
		return responseService.getSingleResult(userJpaRepo.save(user));
	}
	
	@ApiOperation(value = "ȸ������", notes = "ȸ�������� �����Ѵ�.")
	@PutMapping(value = "/user")
	public SingleResult<User> modify(
			@ApiParam(value = "ȸ����ȣ", required = true) @RequestParam long msrl,
			@ApiParam(value = "ȸ�����̵�", required = true) @RequestParam String uid,
			@ApiParam(value = "ȸ���̸�", required = true) @RequestParam String name
			){
		User user = User.builder()
				.msrl(msrl)
				.uid(uid)
				.name(name)
				.build();
		return responseService.getSingleResult(userJpaRepo.save(user));
		
	}
	
	@ApiOperation(value = "ȸ������", notes = "userId�� ȸ�������� �����Ѵ�.")
	public CommonResult delete(
			@ApiParam(value = "ȸ����ȣ", required = true) @PathVariable long msrl
			) {
		userJpaRepo.deleteById(msrl);
		return responseService.getSuccessResult();
	}
	
}
