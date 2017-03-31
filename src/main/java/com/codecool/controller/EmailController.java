package com.codecool.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class EmailController {

    @RequestMapping
    public String status() {
        return "OK";
    }
}
