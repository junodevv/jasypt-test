package com.example.jasypt.mvc;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final TestService service;

    @GetMapping("/Test/{data}")
    public String addData(@PathVariable String data){
        service.addData(data);
        return "성공";
    }
}
