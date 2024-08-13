package com.fisa.land.fisaland.common.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fisa.land.fisaland.common.dto.request.LoginDTO;
import com.fisa.land.fisaland.common.dto.request.UserDTO;
import com.fisa.land.fisaland.common.dto.response.UserResponseDTO;
import com.fisa.land.fisaland.common.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("common")
public class UserController {
	
	private final UserService userService;
	
	// 회원가입
	@PostMapping("user/register")
	public void register(@RequestBody UserDTO user) {
		System.out.println("register() is called!");
		userService.register(user);
	}

	// 로그인
	@PostMapping("user/login")
	public UserResponseDTO login(@RequestBody LoginDTO loginDTO, HttpSession session) {
		System.out.println("login() is called!");
		UserResponseDTO userDto = userService.login(loginDTO);
		
		// 로그인 성공 시 세션에 id 값을 저장
		Long userId = userService.findByEmail(userDto.getEmail());
        session.setAttribute("userId", userId);
        
		return userDto;
	}
	
	// 로그아웃
	@PostMapping("user/logout")
	public void logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		System.out.println("logout() is called!");
	
		session.invalidate();
		session = null;
		
		System.out.println("logout success !!");
	}
	
	// User 정보 조회
	@GetMapping("user")
	public UserDTO getUser(Long userId) {
//		userService.findByUserId(userId);
		System.out.println("getUser() is called!");
		return new UserDTO();
	}

	// 회원 정보 수정
	@PutMapping("user")
	public UserDTO updateUser(@RequestBody UserDTO user) {
		System.out.println("updateUser() is called!");
		return new UserDTO();
	}

	// 회원 탈퇴
	@DeleteMapping("user")
	public void deleteUser(@RequestBody String id) {
		System.out.println("deleteUser() is called!");
		return;
	}

}
