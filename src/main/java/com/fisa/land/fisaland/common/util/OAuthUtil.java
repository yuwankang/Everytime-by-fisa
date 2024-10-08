package com.fisa.land.fisaland.common.util;

import com.fisa.land.fisaland.common.dto.response.AuthDTO;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class OAuthUtil {
	@Value("${security.oauth2.google.authentication_url}")
	private String GOOGLE_AUTHENTICATION_URL;
	@Value("${security.oauth2.google.token_url}")
	private String GOOGLE_TOKEN_URL;
	@Value("${security.oauth2.google.user_info_url}")
	private String GOOGLE_USER_INFO_URL;
	@Value("${security.oauth2.google.client_id}")
	private String GOOGLE_CLIENT_ID;
	@Value("${security.oauth2.google.client_secret}")
	private String GOOGLE_CLIENT_SECRET;
	@Value("${security.oauth2.google.redirect_uri}")
	private String GOOGLE_REDIRECT_URI;

	// kakao
	@Value("${security.oauth2.kakao.authentication_url}")
	private String KAKAO_AUTHENTICATION_URL;
	@Value("${security.oauth2.kakao.rest_api_key}")
	private String KAKAO_REST_API_KEY;
	@Value("${security.oauth2.kakao.redirect_uri}")
	private String KAKAO_REDIRECT_URI;
	@Value("${security.oauth2.kakao.token_url}")
	private String KAKAO_TOKEN_URL;
	@Value("${security.oauth2.kakao.user_info_url}")
	private String KAKAO_USER_INFO_URL;
	
	//github
	@Value("${security.oauth2.github.authentication_url}")
	private String GITHUB_AUTHENTICATION_URL;
	@Value("${security.oauth2.github.client_id}")
	private String GITHUB_CLIENT_ID;
	@Value("${security.oauth2.github.redirect_uri}")
	private String GITHUB_REDIRECT_URI;
	@Value("${security.oauth2.github.token_url}")
	private String GITHUB_TOKEN_URL;
	@Value("${security.oauth2.github.user_info_url}")
	private String GITHUB_USER_INFO_URL;
	@Value("${security.oauth2.github.client_secret}")
	private String GITHUB_CLIENT_SECRET;

	private final RestTemplate restTemplate = new RestTemplate();

	public String getGoogleRedirectUrl() {
		return GOOGLE_AUTHENTICATION_URL + "?client_id=" + GOOGLE_CLIENT_ID + "&redirect_uri=" + GOOGLE_REDIRECT_URI
				+ "&response_type=code&scope=https://www.googleapis.com/auth/userinfo.email+https://www.googleapis.com/auth/userinfo.profile";
	}

	public String getKakaoRedirectUrl() {
		return KAKAO_AUTHENTICATION_URL + "?client_id=" + KAKAO_REST_API_KEY + "&redirect_uri=" + KAKAO_REDIRECT_URI
				+ "&response_type=code";
	}

	public String getGithubRedirectUrl() {
		return GITHUB_AUTHENTICATION_URL + "?client_id=" + GITHUB_CLIENT_ID + "&redirect_uri=" + GITHUB_REDIRECT_URI;
	}

	public String getGoogleAccessToken(String code) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		MultiValueMap<String, String> params = new org.springframework.util.LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", GOOGLE_CLIENT_ID);
		params.add("client_secret", GOOGLE_CLIENT_SECRET);
		params.add("redirect_uri", GOOGLE_REDIRECT_URI);
		params.add("code", code);

		HttpEntity<MultiValueMap<String, String>> googleTokenRequest = new HttpEntity<>(params, httpHeaders);

		ResponseEntity<String> response = restTemplate.exchange(GOOGLE_TOKEN_URL, HttpMethod.POST, googleTokenRequest,
				String.class);
		return JsonParser.parseString(Objects.requireNonNull(response.getBody())).getAsJsonObject().get("access_token")
				.getAsString();
	}

	public AuthDTO.MemberInformation getGoogleUserInfo(String accessToken) {
		HttpHeaders httpHeaders = new HttpHeaders();

		httpHeaders.add("Authorization", "Bearer " + accessToken);
		httpHeaders.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		HttpEntity<MultiValueMap<String, String>> googleUserInfoRequest = new HttpEntity<>(httpHeaders);

		ResponseEntity<String> response = restTemplate.exchange(GOOGLE_USER_INFO_URL, HttpMethod.GET,
				googleUserInfoRequest, String.class);

		JsonElement element = JsonParser.parseString(Objects.requireNonNull(response.getBody()));
		JsonObject jsonObject = element.getAsJsonObject();
		AuthDTO.MemberInformation memberInformation = AuthDTO.MemberInformation.builder()
				.socialId(jsonObject.get("sub").getAsString()).name(jsonObject.get("given_name").getAsString())
				.postUrl(jsonObject.get("picture").getAsString()).email(jsonObject.get("email").getAsString()).build();

		return memberInformation;
	}

	public String getKakaoAccessToken(String code) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		MultiValueMap<String, String> params = new org.springframework.util.LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", KAKAO_REST_API_KEY); // Replace with your actual REST_API_KEY
		params.add("redirect_uri", KAKAO_REDIRECT_URI);
		params.add("code", code);

		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, httpHeaders);

		ResponseEntity<String> response = restTemplate.exchange(KAKAO_TOKEN_URL,
				org.springframework.http.HttpMethod.POST, kakaoTokenRequest, String.class);
		return JsonParser.parseString(Objects.requireNonNull(response.getBody())).getAsJsonObject().get("access_token")
				.getAsString();
	}

	public AuthDTO.MemberInformation getKakaoUserInfo(String accessToken) {
		HttpHeaders httpHeaders = new HttpHeaders();

		httpHeaders.add("Authorization", "Bearer " + accessToken);
		httpHeaders.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		HttpEntity<MultiValueMap<String, String>> kakaoUserInfoRequest = new HttpEntity<>(httpHeaders);

		ResponseEntity<String> response = restTemplate.exchange(KAKAO_USER_INFO_URL,
				org.springframework.http.HttpMethod.POST, kakaoUserInfoRequest, String.class);

		JsonElement element = JsonParser.parseString(Objects.requireNonNull(response.getBody()));
		JsonObject jsonObject = element.getAsJsonObject();
		AuthDTO.MemberInformation memberInformation = AuthDTO.MemberInformation.builder()
				.socialId(jsonObject.get("id").getAsString())
				.name(jsonObject.getAsJsonObject("properties").get("nickname").getAsString()) // Get the nickname
				.postUrl(jsonObject.getAsJsonObject("properties").get("profile_image").getAsString()) // Get the profile
																										// image URL
				.email(jsonObject.getAsJsonObject("kakao_account").get("email").getAsString()).build();

		return memberInformation;
	}
	
	public String getGithubAccessToken(String code) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		MultiValueMap<String, String> params = new org.springframework.util.LinkedMultiValueMap<>();
		params.add("client_id", GITHUB_CLIENT_ID); // Replace with your actual REST_API_KEY
		params.add("client_secret", GITHUB_CLIENT_SECRET);
		params.add("code", code);
		params.add("redirect_uri", GITHUB_REDIRECT_URI);

		HttpEntity<MultiValueMap<String, String>> githubTokenRequest = new HttpEntity<>(params, httpHeaders);

		ResponseEntity<String> response = restTemplate.exchange(GITHUB_TOKEN_URL,
				org.springframework.http.HttpMethod.POST, githubTokenRequest, String.class);
		
		  // 정규식 패턴을 사용하여 access_token 값 추출
        Pattern pattern = Pattern.compile("access_token=([^&]+)");
        Matcher matcher = pattern.matcher(response.toString());
        String accessToken = null;
        // access_token 값이 존재하면 출력
        if (matcher.find()) {
            accessToken = matcher.group(1);
        } else {
            System.out.println("Access Token not found.");
        }
		return accessToken;
	}
	
	public AuthDTO.MemberInformation getGithubUserInfo(String accessToken) {
		HttpHeaders httpHeaders = new HttpHeaders();

		System.out.println(accessToken);
		httpHeaders.add("Authorization", "Bearer " + accessToken);
		httpHeaders.add("Content-type", "application/json;charset=utf-8");

		HttpEntity<MultiValueMap<String, String>> githubUserInfoRequest = new HttpEntity<>(httpHeaders);

		ResponseEntity<String> response = restTemplate.exchange(GITHUB_USER_INFO_URL,
				org.springframework.http.HttpMethod.GET, githubUserInfoRequest, String.class);

		JsonElement element = JsonParser.parseString(Objects.requireNonNull(response.getBody()));
		JsonObject jsonObject = element.getAsJsonObject();
		AuthDTO.MemberInformation memberInformation = AuthDTO.MemberInformation.builder()
				.socialId(jsonObject.get("id").getAsString())
				.name(jsonObject.get("login").getAsString()) // Get the nickname
				.postUrl(jsonObject.get("avatar_url").getAsString()) // Get the profile		// image URL
				.email(jsonObject.get("html_url").getAsString()).build();

		return memberInformation;
	}

}
