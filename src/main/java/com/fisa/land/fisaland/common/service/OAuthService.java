package com.fisa.land.fisaland.common.service;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fisa.land.fisaland.common.dto.response.AuthDTO;
import com.fisa.land.fisaland.common.type.LoginProvider;
import com.fisa.land.fisaland.common.util.OAuthUtil;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class OAuthService{

	@Autowired
    private PasswordEncoder passwordEncoder;
	@Autowired
    private OAuthUtil oAuth2Util;
//	@Autowired
//    private MemberRepository memberRepository;
    public String getRedirectUrl(LoginProvider provider) {
        if (provider == LoginProvider.GOOGLE) {
            return oAuth2Util.getGoogleRedirectUrl();
        }
        return null;
    }

    public String getAccessToken(String authorizationCode, LoginProvider provider) {
        String accessToken = null;
        if (provider == LoginProvider.GOOGLE) {
            accessToken = oAuth2Util.getGoogleAccessToken(authorizationCode);
        }
        return accessToken;
    }

    public void login(String accessToken, LoginProvider provider) throws IOException {
        AuthDTO.MemberInformation memberInformation;
        if (provider == LoginProvider.GOOGLE) {
            memberInformation = oAuth2Util.getGoogleUserInfo(accessToken);
        } else {
            memberInformation = null;
        }

        if (memberInformation == null) {
            throw new RuntimeException();
        }

//        Member member = memberRepository.findBySocialIdAndLoginProvider(memberInformation.getSocialId(), provider)
//                .orElseGet(() ->
//                        memberRepository.save(Member.builder()
//                                .socialId(memberInformation.getSocialId())
//                                .loginProvider(provider)
//                                .name(memberInformation.getName())
//                                .email(memberInformation.getEmail())
//                                .password(passwordEncoder.encode(memberInformation.getSocialId()))
//                                .imgUrl(memberInformation.getPostUrl())
//                                .memberRoleList(Arrays.asList(MemberRole.USER))
//                                .build())
//                );
//
//        MemberSecurityDTO memberSecurityDTO =
//                new MemberSecurityDTO(member.getSocialId(), member.getPassword(), member.getMemberRoleList().stream().map(Enum::toString).collect(Collectors.toList()), member.getName());

//        Map<String, Object> claims = memberSecurityDTO.getClaims();
//
//        String jwtToken = JWTUtil.generateToken(memberSecurityDTO.getClaims(), 24 * 60 * 36); //지금 당장 사용할 수 있는 권리
//        String jwtRefreshToken = JWTUtil.generateToken(memberSecurityDTO.getClaims(), 24 * 60 * 36); //교환권
//
//        claims.put("accessToken", jwtToken);
//        claims.put("refreshToken", jwtRefreshToken);
//
//        return "http://localhost:3000/login?accessToken=" + jwtToken + "&refreshToken=" + jwtRefreshToken;
    }

}