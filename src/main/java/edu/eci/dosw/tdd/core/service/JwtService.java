package edu.eci.dosw.tdd.core.service;

import java.util.Set;

public interface JwtService {

    String generateToken(Long userId, String username, Set<String> roles);

    String extractUsername(String token);

    Long extractUserId(String token);

    Set<String> extractRoles(String token);

    boolean isTokenValid(String token);
}