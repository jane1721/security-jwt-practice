package com.jane.securityjwtpractice.auth.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * JWT 발급 & 검증 유틸리티
 */
@Component
public class JwtProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    /**
     * 인증 정보를 기반으로 JWT 토큰을 생성
     *
     * @param authentication Spring Security 의 인증 객체
     * @return 생성된 JWT 문자열
     */
    public String generateToken(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        Date now = new Date();
        long expirationMilliseconds = 1000 * 60 * 60; // 1시간
        Date expiry = new Date(now.getTime() + expirationMilliseconds);

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .compact();
    }

    /**
     * JWT 토큰에서 username(subject) 을 추출
     *
     * @param token JWT 문자열
     * @return 사용자 이름
     */
    public String getUsername(String token) {
        return parseClaims(token).getSubject();
    }

    /**
     * JWT 토큰 유효성 검사
     *
     * @param token JWT 문자열
     * @return 유효하면 true, 유효하지 않으면 false
     */
    public boolean validateToken(String token) {
        try {
            parseClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * JWT 토큰에서 Claims(본문 정보) 추출
     *
     * @param token JWT 문자열
     * @return Claims 객체
     */
    private Claims parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes())) // 서명 키 설정
                .build()
                .parseClaimsJws(token) // 파싱
                .getBody(); // Claims 반환
    }
}
