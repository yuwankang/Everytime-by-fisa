package com.fisa.land.fisaland.common.controller;

import com.fisa.land.fisaland.common.dto.response.JWTAuthResponse;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fisa.land.fisaland.common.dto.request.LoginDTO;
import com.fisa.land.fisaland.common.dto.request.UserDTO;
import com.fisa.land.fisaland.common.dto.response.UserResponseDTO;
import com.fisa.land.fisaland.common.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Tag(name = "사용자 API", description = "사용자 등록, 조회, 수정, 로그인 및 로그아웃 API")
@RestController
@RequestMapping("common")
@RequiredArgsConstructor
public class UserController {
    private static final Logger LOGGER = Logger.getLogger(UserController.class);

    private final UserService userService;

    // 회원가입
    @Operation(summary = "회원가입", description = "신규 사용자를 등록하는 API")
    @PostMapping("user/register")
    public void register(@RequestBody UserDTO user) {
        LOGGER.info("회원가입 호출됨: 사용자 정보: " + user);
        userService.register(user);
        LOGGER.info("사용자 등록 성공: 이메일: " + user.getEmail());
    }


    // 로그인
    @Operation(summary = "로그인", description = "사용자를 인증하고 세션에 ID 값을 저장하는 API")
    @PostMapping("user/login")
    public JWTAuthResponse login(@RequestBody LoginDTO loginDTO) {
        LOGGER.info("로그인 호출됨: 로그인 정보: " + loginDTO);
        return userService.login(loginDTO);
    }

    // 로그아웃
    @Operation(summary = "로그아웃", description = "사용자를 로그아웃하고 세션을 무효화하는 API")
    @PostMapping("user/logout")
    public void logout(HttpServletRequest request) {
        System.out.println("logout() is called!");
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
            session = null;
        }
        System.out.println("logout success !!");
    }

    // User 정보 조회
    @Operation(summary = "사용자 정보 조회", description = "사용자 ID로 사용자 정보를 조회하는 API")
    @GetMapping("user/{userId}")
    public UserResponseDTO getUser(@PathVariable("userId") Long userId) {
        LOGGER.info("사용자 정보 조회 호출됨: 사용자 ID: " + userId);
        UserResponseDTO userDto = userService.getUser(userId);
        LOGGER.info("사용자 정보 조회 성공: " + userDto);
        return userDto;
    }

    // 사용자 정보 수정
    @Operation(summary = "사용자 정보 수정", description = "사용자 정보를 수정하는 API")
    @PutMapping("user")
    public UserResponseDTO updateUser(@RequestBody UserDTO user) {
        LOGGER.info("사용자 정보 수정 호출됨: 사용자 정보: " + user);
        UserResponseDTO userDto = userService.updateUser(user);
        LOGGER.info("사용자 정보 수정 성공: " + userDto);
        return userDto;
    }

    // 사용자 계정 비활성화
    @Operation(summary = "사용자 계정 비활성화", description = "사용자 계정을 비활성화(탈퇴)하는 API")
    @DeleteMapping("user/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId) {
        LOGGER.info("사용자 계정 비활성화 호출됨: 사용자 ID: " + userId);
        userService.deleteUser(userId);
        LOGGER.info("사용자 계정 비활성화 성공: 사용자 ID: " + userId);
    }
}
