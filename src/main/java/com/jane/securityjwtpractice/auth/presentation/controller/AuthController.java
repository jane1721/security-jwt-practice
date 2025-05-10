package com.jane.securityjwtpractice.auth.presentation.controller;

import com.jane.securityjwtpractice.user.domain.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest request) {
        userService.signup(request.getUsername(), request.getPassword());
        return ResponseEntity.ok("회원가입 완료");
    }

    @Getter @Setter
    static class SignupRequest {
        private String username;
        private String password;
    }
}
