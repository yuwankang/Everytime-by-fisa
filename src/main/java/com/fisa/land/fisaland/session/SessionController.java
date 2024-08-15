package com.fisa.land.fisaland.session;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/session")
@Tag(name = "세션 관리 API", description = "사용자 세션 정보를 관리하는 API")
public class SessionController {

    @Operation(summary = "세션 정보 조회", description = "현재 세션에 저장된 사용자 ID를 조회합니다.")
    @GetMapping("/data")
    public ResponseEntity<Map<String, Object>> getSessionData(HttpSession session) {
        Map<String, Object> response = new HashMap<>();

        Long userId = (Long) session.getAttribute("userId");
        if (userId != null) {
            response.put("userId", userId);
            return ResponseEntity.ok(response);
        } else {
            response.put("error", "사용자가 로그인되어 있지 않습니다.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
}
