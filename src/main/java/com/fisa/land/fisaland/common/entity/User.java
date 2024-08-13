package com.fisa.land.fisaland.common.entity;

import java.time.LocalDateTime;

import com.fisa.land.fisaland.common.BaseTimeEntity;
import com.fisa.land.fisaland.common.dto.request.UserDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Entity

@Table(name = "User")
public class User extends BaseTimeEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	@Column(name = "username", nullable = false, length = 16)
	private String username;

	@Column(name = "email", nullable = false, length = 50)
	private String email;

	@Column(name = "password", nullable = false, length = 50)
	private String password;

	@Column(name = "phone", length = 20)
	private String phone;

	@Column(name = "class", length = 20)
	private String userClass;
	

	public User update(UserDTO userDto) {
		this.username = userDto.getUsername();
		this.email = userDto.getEmail();
		this.password = userDto.getPassword();
		this.phone = userDto.getPhone();
		this.userClass = userDto.getUserClass();
		return this;
	}
}