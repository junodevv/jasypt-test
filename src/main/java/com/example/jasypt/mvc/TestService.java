package com.example.jasypt.mvc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestService {

    private final TestRepository repository;

    public String addData(String data){
        TestData data1 = TestData.builder().data(data).build();
        repository.save(data1);
        return "성공";
    }
}
