package com.example.user_service.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.security.Key;

@Service
public class DecodeToken {

    private static final String SECRET = "aHa66Jus+2XX03YJO7MFcs6RnN4eHCsjLmmcIATMmjEi7RctICJiZzI3dzWZrh2q";

    public static String decodeToken(String _token) {
        Key key = Keys.hmacShaKeyFor(SECRET.getBytes());
        try {
            Jws<Claims> jwsClaims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(_token);
            return jwsClaims.getBody().getSubject();
        } catch (RuntimeException _e) {
            return null;
        }

    }

    public String getTokenFromCookies(HttpServletRequest request) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("access_token".equals(cookie.getName())) {
                    return decodeToken(cookie.getValue());
                }
            }
        }
        return null;
    }
}
