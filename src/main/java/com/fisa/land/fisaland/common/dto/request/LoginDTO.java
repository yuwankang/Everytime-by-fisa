package com.fisa.land.fisaland.common.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {

    @NotNull(message = "이메일 주소는 필수입니다.")
    @Schema(description = "사용자의 이메일 주소", example = "user@example.com")
    private String email;

    @NotNull(message = "비밀번호는 필수입니다.")
    @Schema(description = "사용자의 비밀번호", example = "password123")
    private String pw;

    //로그인 로그 추출시 pw 가져오는 버그 방지
    @Override
    public String toString() {
        return "LoginDTO(email=" + email + ")";
    }
}
