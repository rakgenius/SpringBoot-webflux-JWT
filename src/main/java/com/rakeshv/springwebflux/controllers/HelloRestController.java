package com.rakeshv.springwebflux.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloRestController {
    @GetMapping("/rest")
    public ResponseEntity<String> greet(@RequestParam(defaultValue = "name") String name) {
        return new ResponseEntity<>("Hello " + name, HttpStatus.OK);
    }
}
