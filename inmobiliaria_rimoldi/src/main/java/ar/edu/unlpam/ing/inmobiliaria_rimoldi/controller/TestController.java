package ar.edu.unlpam.ing.inmobiliaria_rimoldi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping
    public String test() {
        return "Test OK";
    }
}
