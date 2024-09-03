package com.fisa.land.fisaland.common.service;

import java.util.Optional;

import com.fisa.land.fisaland.common.config.JwtTokenProvider;
import com.fisa.land.fisaland.common.dto.response.JWTAuthResponse;
import com.fisa.land.fisaland.common.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fisa.land.fisaland.common.dto.request.LoginDTO;
import com.fisa.land.fisaland.common.dto.request.UserDTO;
import com.fisa.land.fisaland.common.dto.response.UserResponseDTO;
import com.fisa.land.fisaland.common.exception.ExceptionList;
import com.fisa.land.fisaland.common.exception.BusinessLoginException;
import com.fisa.land.fisaland.common.respository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final JwtTokenProvider jwtTokenProvider;
    private final BCryptPasswordEncoder pwdEncoder;
    private final AuthenticationManager authenticationManager;
    private final MyUserDetailsService myUserDetailsService;
    private final UserRepository userRepository;
    private final ModelMapper mapper;

    @Transactional
    @Override
    public void register(UserDTO userDto) {

        // 이메일 중복 확인
        Optional<UserEntity> existingUser = userRepository.findByEmail(userDto.getEmail());
        if (existingUser.isPresent()) {
            throw new BusinessLoginException(ExceptionList.EMAIL_ALREADY_EXISTS);
        }

        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserEntity userEntity = mapper.map(userDto, UserEntity.class);
        userEntity.setEncryptedPwd(pwdEncoder.encode(userDto.getPassword()));
        userRepository.save(userEntity);
    }


    @Override
    public JWTAuthResponse login(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDTO.getEmail(), loginDTO.getPw()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        Long userId = myUserDetailsService.findUserIdByEmail(loginDTO.getEmail()); //1
        String name = myUserDetailsService.findNameByEmail(loginDTO.getEmail()); //fisa@gmail.com
        JWTAuthResponse token = jwtTokenProvider.generateToken(authentication, userId, name);
        return token;
    }

    @Override
    public UserResponseDTO getUser(Long userId) {
        UserEntity userEntity = userRepository.findByUserId(userId)
                .orElseThrow(() -> new BusinessLoginException(ExceptionList.USER_NOT_FOUND));

        if (!userEntity.isActivated()) {
            throw new BusinessLoginException(ExceptionList.USER_NOT_ACTIVATED);
        }

        return mapper.map(userEntity, UserResponseDTO.class);
    }

    @Override
    public Long findByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessLoginException(ExceptionList.USER_NOT_FOUND));

        if (!userEntity.isActivated()) {
            throw new BusinessLoginException(ExceptionList.USER_NOT_ACTIVATED);
        }

        return userEntity.getUserId();
    }

    @Transactional
    @Override
    public UserResponseDTO updateUser(UserDTO userDto) {
        UserEntity userEntity = userRepository.findByUserId(userDto.getUserId())
                .orElseThrow(() -> new BusinessLoginException(ExceptionList.USER_NOT_FOUND));

        if (!userEntity.isActivated()) {
            throw new BusinessLoginException(ExceptionList.USER_NOT_ACTIVATED);
        }

        //업데이트 시간만 업데이트
        userEntity.update(userDto);
        userEntity.setEncryptedPwd(pwdEncoder.encode(userDto.getPassword()));
        userRepository.save(userEntity);

        return mapper.map(userEntity, UserResponseDTO.class);
    }


    @Transactional
    @Override
    public void deleteUser(Long userId) {
        UserEntity userEntity = userRepository.findByUserId(userId)
                .orElseThrow(() -> new BusinessLoginException(ExceptionList.USER_NOT_FOUND));

        if (!userEntity.isActivated()) {
            throw new BusinessLoginException(ExceptionList.USER_NOT_ACTIVATED);
        }

        userEntity.setActivated(false);
        userRepository.save(userEntity);
    }
}

