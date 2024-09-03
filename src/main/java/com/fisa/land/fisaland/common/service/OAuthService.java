package com.fisa.land.fisaland.common.service;

import com.fisa.land.fisaland.common.entity.UserEntity;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fisa.land.fisaland.common.dto.response.AuthDTO;
import com.fisa.land.fisaland.common.respository.UserRepository;
import com.fisa.land.fisaland.common.type.LoginProvider;
import com.fisa.land.fisaland.common.util.OAuthUtil;

import java.io.IOException;

@Service
@Transactional
@RequiredArgsConstructor
public class OAuthService{

    public final PasswordEncoder passwordEncoder;

    @Autowired
    private OAuthUtil oAuth2Util;

    @Autowired
    private UserRepository userRepository;
    public String getRedirectUrl(LoginProvider provider) {
        if (provider == LoginProvider.GOOGLE) {
            return oAuth2Util.getGoogleRedirectUrl();
        }else if(provider == LoginProvider.KAKAO) {
        	return oAuth2Util.getKakaoRedirectUrl();
        }
        return null;
    }

    public String getAccessToken(String authorizationCode, LoginProvider provider) {
        String accessToken = null;
        if (provider == LoginProvider.GOOGLE) {
            accessToken = oAuth2Util.getGoogleAccessToken(authorizationCode);
        }else if(provider == LoginProvider.KAKAO) {
        	accessToken = oAuth2Util.getKakaoAccessToken(authorizationCode);
        }
        return accessToken;
    }

    public void login(String accessToken, LoginProvider provider) throws IOException {
        AuthDTO.MemberInformation memberInformation;
        if (provider == LoginProvider.GOOGLE) {
            memberInformation = oAuth2Util.getGoogleUserInfo(accessToken);
        }else if(provider == LoginProvider.KAKAO) {
        	memberInformation = oAuth2Util.getKakaoUserInfo(accessToken);
        }else {
            memberInformation = null;
        }

        if (memberInformation == null) {
            throw new RuntimeException();
        }

        System.out.println(memberInformation);
        UserEntity userEntity = userRepository.findBySocialIdAndLoginProvider(memberInformation.getSocialId(), provider)
                .orElseGet(()->
                                userRepository.save(UserEntity.builder()
                                        .socialId(memberInformation.getSocialId())
                                        .loginProvider(provider)
                                        .username(memberInformation.getName())
                                        .email(memberInformation.getEmail())
//								.password(passwordEncoder.encode(memberInformation.getSocialId()))
                                        .imgUrl(memberInformation.getPostUrl())
                                        .build())
                );
    }

}