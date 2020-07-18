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
@RequiredArgsConstructor // class 상단에 선언해서 final 선언된 객체에 생성자 주입을 한다. 사용하지 않고 autowired를 사용해되 가능.
@RestController // 결과값을 json으로 표시
@RequestMapping(value = "/v1")
public class UserController {
	
	private final UserJpaRepo userJpaRepo;
	private final ResponseService responseService; // 결과를 처리할 service
	
	@ApiOperation(value = "회원 리스트 조회", notes = "모든회원을 조회한다.")
	@GetMapping(value = "/users")
	public ListResult<User> findAllUser(){
		// 결과 데이터가 여러건인 경우 getListResult를 이용해서 결과를 출력한다.		
		return responseService.getListResult(userJpaRepo.findAll());
	}
	
	@ApiOperation(value = "회원 단건 조회", notes = "userId로 회원을 조회한다.")
	@GetMapping(value = "/user/{msrl}")
	public SingleResult<User> findUserById(
			@ApiParam(value = "회원ID", required = true) @PathVariable long msrl
			){
		// 결과데이터가 단일건인경우 getSingleResult를 이용해서 결과를 출력한다.
		return responseService.getSingleResult(userJpaRepo.findById(msrl).orElse(null));
	}
	
	@ApiOperation(value = "회원 입력", notes = "회원을 입력한다.")
	@PostMapping(value = "/user")
	public SingleResult<User> save(
			@ApiParam(value = "회원 아이디", required = true) @RequestParam String uid
			, @ApiParam(value = "회원이름", required = true) @RequestParam String name
			) {
		User user = User.builder()
				.uid(uid)
				.name(name)
				.build();
		return responseService.getSingleResult(userJpaRepo.save(user));
	}
	
	@ApiOperation(value = "회원수정", notes = "회원정보를 수정한다.")
	@PutMapping(value = "/user")
	public SingleResult<User> modify(
			@ApiParam(value = "회원번호", required = true) @RequestParam long msrl,
			@ApiParam(value = "회원아이디", required = true) @RequestParam String uid,
			@ApiParam(value = "회원이름", required = true) @RequestParam String name
			){
		User user = User.builder()
				.msrl(msrl)
				.uid(uid)
				.name(name)
				.build();
		return responseService.getSingleResult(userJpaRepo.save(user));
		
	}
	
	@ApiOperation(value = "회원삭제", notes = "userId로 회원정보를 삭제한다.")
	public CommonResult delete(
			@ApiParam(value = "회원번호", required = true) @PathVariable long msrl
			) {
		userJpaRepo.deleteById(msrl);
		return responseService.getSuccessResult();
	}
	
}
