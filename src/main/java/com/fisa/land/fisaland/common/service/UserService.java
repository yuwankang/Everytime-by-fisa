package com.fisa.land.fisaland.common.service;

import com.fisa.land.fisaland.common.dto.request.LoginDTO;
import com.fisa.land.fisaland.common.dto.request.UserDTO;
import com.fisa.land.fisaland.common.dto.response.UserResponseDTO;

public interface UserService {

	void register(UserDTO user);

	UserResponseDTO login(LoginDTO user);
	
	Long findByEmail(String email);
	
}
