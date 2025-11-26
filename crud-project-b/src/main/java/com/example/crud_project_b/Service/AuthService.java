package com.example.crud_project_b.Service;

import com.example.crud_project_b.Repository.RefreshTokenRepository;
import com.example.crud_project_b.domain.RefreshToken;
import com.example.crud_project_b.dto.TokenResponse;
import com.example.crud_project_b.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtUtil jwtUtil;
    private final RefreshTokenRepository refreshTokenRepository;
    private final CustomUserDetailsService customUserDetailsService;

    public TokenResponse login(String username, String password) {
        UserDetails user = customUserDetailsService.loadUserByUsername(username);

        if (!password.equals(user.getPassword())) {
            throw new RuntimeException("비밀번호 불일치");
        }

        String access = jwtUtil.createAccessToken(username);
        String refresh = jwtUtil.createRefreshToken(username);

        refreshTokenRepository.save(new RefreshToken(access, refresh));

        return new TokenResponse(access, refresh);
    }

    public TokenResponse reissue(String refreshToken) {
        if (!jwtUtil.validate(refreshToken)) {
            throw new RuntimeException("Refresh Token 만료");
        }

        String username = jwtUtil.getUsername(refreshToken);
        RefreshToken saved = refreshTokenRepository.findById(username)
                .orElseThrow(() -> new RuntimeException("Refresh Token 없음"));

        if (!saved.getRefreshToken().equals(refreshToken)) {
            throw new RuntimeException("Refresh Token 불일치");
        }

        String newAccess = jwtUtil.createAccessToken(username);

        return new TokenResponse(newAccess, refreshToken);

    }
}
