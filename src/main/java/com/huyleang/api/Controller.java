package com.huyleang.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/huyleang")
class Controller {
    @GetMapping
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello World!");
    }
}