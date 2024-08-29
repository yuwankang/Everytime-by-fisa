package com.fisa.land.fisaland.common.dto.response;

import lombok.*;

public class AuthDTO {
    @Getter
    @Builder
    @AllArgsConstructor
    @ToString
    public static class MemberNameDto{
        private Long memberId;
        private String name;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @ToString
    public static class MemberInformation{
        private String socialId;
        private String email;
        private String name;
        private String postUrl;
    }
}
