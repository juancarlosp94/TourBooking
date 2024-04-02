package com.group5.tourbooking.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.Collectors;

public class JwtUtils {

    public static final String PREFIX = "Bearer ";
    private static final String SECRET = "12345678";
    private static final int EXPIRATION = 60; // minutos

    public static String generateJWT(UserDetails userDetails) {
        return PREFIX + JWT.create()
                .withSubject(userDetails.getUsername())
                .withClaim("role", userDetails.getAuthorities().stream()
                        .map(authority -> authority.getAuthority())
                        .collect(Collectors.toList()))
                .withExpiresAt(Instant.now().plus(Duration.ofMinutes(EXPIRATION)))
                .sign(Algorithm.HMAC512(SECRET));
    }

    public static void validate(String token) {
        JWT.require(Algorithm.HMAC512(SECRET))
                .build()
                .verify(token);
    }

    public static String getUsername(String token) {
        return JWT.decode(token).getSubject();
    }

    public static String getRole(String token) {
        return JWT.decode(token).getClaim("role").asString();
    }

}
