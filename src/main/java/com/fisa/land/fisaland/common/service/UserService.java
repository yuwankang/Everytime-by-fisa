package com.fisa.land.fisaland.common.service;

import org.springframework.stereotype.Service;

import com.fisa.land.fisaland.common.dto.UserDTO;

public interface UserService {

	void register(UserDTO user);
	
}
