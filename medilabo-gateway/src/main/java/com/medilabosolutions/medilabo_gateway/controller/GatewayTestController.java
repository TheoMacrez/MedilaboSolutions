package com.medilabosolutions.medilabo_gateway.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class GatewayTestController {

    @GetMapping
    public ResponseEntity<String> testGateway() {
        return ResponseEntity.ok("Le Gateway fonctionne !");
    }
}

