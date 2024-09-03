package com.fisa.land.fisaland.common.entity;

import java.time.LocalDateTime;

import com.fisa.land.fisaland.common.dto.request.UserDTO;
import com.fisa.land.fisaland.common.type.LoginProvider;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "User")
public class UserEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long userId;

    @Column(name = "social_id")
    private String socialId;

    @Column(name = "login_provider") //수정
    private LoginProvider loginProvider;

    @Column(name = "username", nullable = false, length = 16)
    private String username;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "password", nullable = false)
    private String encryptedPwd; //암호화 추가

    @Column(name = "img_url", nullable = true) //fix : not null이면 에러 발생해서 수정r
    private String imgUrl;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "class", length = 20)
    private String userClass;

    @Column(name = "is_activated", nullable = false)
    @Builder.Default
    private boolean isActivated = true;

    public UserEntity update(UserDTO userDto) {
        this.username = userDto.getUsername();
        this.email = userDto.getEmail();
        this.encryptedPwd = userDto.getPassword();
        this.phone = userDto.getPhone();
        this.userClass = userDto.getUserClass();
        this.setUpdatedAt(LocalDateTime.now());
        return this;
    }
}
