package com.fisa.land.fisaland.common.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserResponseDTO {

	private String userId;
	
	private String username;

	private String email;

	private String phone;

	private String userClass;
	
    private boolean isActivated;

}
