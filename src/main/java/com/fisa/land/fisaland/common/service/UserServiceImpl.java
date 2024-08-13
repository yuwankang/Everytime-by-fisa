package com.fisa.land.fisaland.common.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import com.fisa.land.fisaland.common.dto.request.LoginDTO;
import com.fisa.land.fisaland.common.dto.request.UserDTO;
import com.fisa.land.fisaland.common.dto.response.UserResponseDTO;
import com.fisa.land.fisaland.common.entity.User;
import com.fisa.land.fisaland.common.respository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	private final UserRepository userRepository;
    private final ModelMapper mapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }
	
	@Override
	public void register(UserDTO userDto) {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        User user = mapper.map(userDto, User.class);
        userRepository.save(user);
	}

	@Override
	public UserResponseDTO login(LoginDTO loginDTO) {
		 Optional<User> userOptional = userRepository.findByEmail(loginDTO.getEmail());
		
		 // 사용자 존재 여부 및 비밀번호 확인
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // 비밀번호가 일치하는지 확인
            if (user.getPassword().equals(loginDTO.getPw())) {
            	 return mapper.map(user, UserResponseDTO.class);
            } throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        } throw new RuntimeException("사용자를 찾을 수 없습니다.");
    }

	@Override
	public UserResponseDTO getUser(Long userId) {
		Optional<User> userOptional = userRepository.findByUserId(userId);
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			return mapper.map(user, UserResponseDTO.class);
		}
		throw new RuntimeException("사용자를 찾을 수 없습니다.");
	}
	
	@Override
	public Long findByEmail(String email) {
		Optional<User> userOptional = userRepository.findByEmail(email);
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			return user.getUserId();
		}
		throw new RuntimeException("사용자를 찾을 수 없습니다.");
	}

	@Override
	public UserResponseDTO updateUser(UserDTO userDto) {
		Optional<User> userOptional = userRepository.findByUserId(userDto.getUserId());
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			
			//회원 정보 수정
			User updateUser = user.update(userDto);
			return mapper.map(updateUser, UserResponseDTO.class);
		
		}throw new RuntimeException("사용자를 찾을 수 없습니다.");
	}

	@Override
	public void deleteUser(Long userId) {
		Optional<User> userOptional = userRepository.findByUserId(userId);
		
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			user.setActivated(false); 
			userRepository.save(user);
			return;
		}throw new RuntimeException("사용자를 찾을 수 없습니다.");
	}
}

