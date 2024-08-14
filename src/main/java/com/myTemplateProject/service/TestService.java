package com.myTemplateProject.service;

import org.springframework.stereotype.Service;

@Service
public class TestService {

    public Integer test(){
        System.out.println("hello world");
        return 1;
    }
}
