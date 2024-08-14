package com.fisa.land.fisaland.common.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import com.fisa.land.fisaland.common.dto.request.LoginDTO;
import com.fisa.land.fisaland.common.dto.request.UserDTO;
import com.fisa.land.fisaland.common.dto.response.UserResponseDTO;
import com.fisa.land.fisaland.common.entity.User;
import com.fisa.land.fisaland.common.exception.ExceptionList;
import com.fisa.land.fisaland.common.exception.BusinessLoginException;
import com.fisa.land.fisaland.common.respository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService{

	private final UserRepository userRepository;
    private final ModelMapper mapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }
	
    @Transactional
	@Override
	public void register(UserDTO userDto) {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        
        // 이메일 중복 확인
        Optional<User> existingUser = userRepository.findByEmail(userDto.getEmail());
        if (existingUser.isPresent()) {
            throw new BusinessLoginException(ExceptionList.EMAIL_ALREADY_EXISTS);
        }
        
        User user = mapper.map(userDto, User.class);
        userRepository.save(user);
	}


    @Override
    public UserResponseDTO login(LoginDTO loginDTO) {
        User user = userRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new BusinessLoginException(ExceptionList.USER_NOT_FOUND));

        if (!user.getPassword().equals(loginDTO.getPw())) {
            throw new BusinessLoginException(ExceptionList.INVALID_PASSWORD);
        }

        if (!user.isActivated()) {
            throw new BusinessLoginException(ExceptionList.USER_NOT_ACTIVATED);
        }

        return mapper.map(user, UserResponseDTO.class);
    }

    @Override
    public UserResponseDTO getUser(Long userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new BusinessLoginException(ExceptionList.USER_NOT_FOUND));

        if (!user.isActivated()) {
            throw new BusinessLoginException(ExceptionList.USER_NOT_ACTIVATED);
        }

        return mapper.map(user, UserResponseDTO.class);
    }

    @Override
    public Long findByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessLoginException(ExceptionList.USER_NOT_FOUND));

        if (!user.isActivated()) {
            throw new BusinessLoginException(ExceptionList.USER_NOT_ACTIVATED);
        }

        return user.getUserId();
    }
    
    @Transactional
    @Override
    public UserResponseDTO updateUser(UserDTO userDto) {
        User user = userRepository.findByUserId(userDto.getUserId())
                .orElseThrow(() -> new BusinessLoginException(ExceptionList.USER_NOT_FOUND));

        if (!user.isActivated()) {
            throw new BusinessLoginException(ExceptionList.USER_NOT_ACTIVATED);
        }

        //업데이트 시간만 업데이트
        user.update(userDto);
        userRepository.save(user);

        return mapper.map(user, UserResponseDTO.class);
    }


    @Transactional
    @Override
    public void deleteUser(Long userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new BusinessLoginException(ExceptionList.USER_NOT_FOUND));

        if (!user.isActivated()) {
            throw new BusinessLoginException(ExceptionList.USER_NOT_ACTIVATED);
        }

        user.setActivated(false);
        userRepository.save(user);
    }
}

