package com.fisa.land.common;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDTO {
	private Long userId;
	private String username;
	private String email;
	private String password;
	private String phone;
	private String userClass;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
