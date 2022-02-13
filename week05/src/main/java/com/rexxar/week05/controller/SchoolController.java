package com.rexxar.week05.controller;

import com.rexxar.week05.pojo.School;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SchoolController {
    
    @Autowired
    School school;

    @RequestMapping("/list")
    public void listStudent() {
        school.ding();
    }
}
