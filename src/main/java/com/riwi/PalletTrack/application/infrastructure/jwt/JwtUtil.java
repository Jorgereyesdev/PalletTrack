package com.riwi.PalletTrack.application.infrastructure.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    @Value("${jwt.secret}") // Leer la clave secreta desde las propiedades
    private String secretKey;

    @Value("${jwt.expiration}") // Leer el tiempo de expiración desde las propiedades
    private long expirationTime;

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    // Método que extrae el email desde el "sub" del token
    public String extractEmailFromSub(String token) {
        String subject = extractAllClaims(token).getSubject();

        // Extraer el email buscando "email=" y delimitando hasta la siguiente coma
        String email = subject.substring(subject.indexOf("email=") + 6, subject.indexOf(",", subject.indexOf("email=")));

        return email;
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    public boolean validateToken(String token, String email) {
        final String extractedEmail = extractEmailFromSub(token); // Extraer el email en lugar del username
        return (extractedEmail.equals(email) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}
