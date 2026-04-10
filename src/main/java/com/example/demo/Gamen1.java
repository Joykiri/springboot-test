package com.example.demo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Gamen1 {

    @GetMapping("/test")
    public String test() {
        return "test";
    }
}