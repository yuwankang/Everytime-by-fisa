package com.fisa.land.fisaland.common.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fisa.land.fisaland.common.service.OAuthService;
import com.fisa.land.fisaland.common.type.LoginProvider;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("auth")
public class AuthController {


	@Autowired
    private OAuthService oAuth2Service;


    //callback url 정보 담아오기
    @GetMapping("/google")
    public Map<String, String> getGoogleRedirectUrl(){
        Map<String, String> map = new HashMap<>();
        map.put("loginUrl", oAuth2Service.getRedirectUrl(LoginProvider.GOOGLE));
        return map;
    }
    
    @GetMapping("/kakao")
    public Map<String, String> getKakaoRedirectUrl(){
        Map<String, String> map = new HashMap<>();
        map.put("loginUrl", oAuth2Service.getRedirectUrl(LoginProvider.KAKAO));
        return map;
    }

    @GetMapping("/callback")
    public void getGoogleAccessToken(@RequestParam("code") String code, @RequestParam("scope") String scope, HttpServletResponse response) throws IOException {
        String accessToken = oAuth2Service.getAccessToken(code, LoginProvider.GOOGLE);
        oAuth2Service.login(accessToken, LoginProvider.GOOGLE);
    }
    
    @GetMapping("/kakao/callback")
    public void getKakaoAccessToken(@RequestParam("code") String code, HttpServletResponse response) throws IOException {
        String accessToken = oAuth2Service.getAccessToken(code, LoginProvider.KAKAO);
        oAuth2Service.login(accessToken, LoginProvider.KAKAO);
    }

}