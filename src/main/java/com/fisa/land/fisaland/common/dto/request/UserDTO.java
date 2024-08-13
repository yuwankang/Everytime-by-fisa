package com.fisa.land.fisaland.common.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class UserDTO {

    @Schema(description = "사용자 ID", example = "1")
    private Long userId;

    @NotNull(message = "사용자 이름은 필수입니다.")
    @Schema(description = "사용자 이름", example = "johndoe", required = true)
    private String username;

    @NotNull(message = "이메일 주소는 필수입니다.")
    @Schema(description = "사용자의 이메일 주소", example = "user@example.com", required = true)
    private String email;

    @NotNull(message = "비밀번호는 필수입니다.")
    @Schema(description = "사용자의 비밀번호", example = "password123", required = true)
    private String password;

    @Schema(description = "사용자의 전화번호", example = "010-1234-5678")
    private String phone;

    @Schema(description = "사용자의 클래스(역할)", example = "admin")
    private String userClass;

    @Schema(description = "계정 생성 시각", example = "2024-08-13T10:00:00")
    private LocalDateTime createdAt;

    @Schema(description = "계정 수정 시각", example = "2024-08-13T10:00:00")
    private LocalDateTime updatedAt;
}
