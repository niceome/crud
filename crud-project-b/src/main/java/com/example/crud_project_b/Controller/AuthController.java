package com.example.crud_project_b.Controller;

import com.example.crud_project_b.Service.AuthService;
import com.example.crud_project_b.dto.LoginRequest;
import com.example.crud_project_b.dto.ReissueRequest;
import com.example.crud_project_b.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public TokenResponse login(@RequestBody LoginRequest request) {
        return authService.login(request.getUsername(), request.getPassword());
    }

    @PostMapping("/reissue")
    public TokenResponse reissue(@RequestBody ReissueRequest request) {
        return authService.reissue(request.getRefreshToken());
    }
}
