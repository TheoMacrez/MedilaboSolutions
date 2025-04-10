package com.medilabosolutions.medilabo_gateway.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/auth")
public class AuthorizationController {

    private static final String SECRET_KEY = "mySecretKey";

    @PostMapping("/login")
    public ResponseEntity<String> login() {

        AuthorizationRequest userConnexion = new AuthorizationRequest();

        if ("user".equals(userConnexion.getUsername()) && "password".equals(userConnexion.getPassword())) {
            String token = Jwts.builder()
                    .setSubject(userConnexion.getUsername())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1h
                    .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), SignatureAlgorithm.HS256)
                    .compact();
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @Setter
    @Getter
    static class AuthorizationRequest {

        private String username = "user";
        private String password = "password";

    }
}
