package com.github.yuki.iwaya.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.yuki.iwaya.service.AmazonBedrockService;

@RestController
@RequestMapping("/api/v1/bedrock")
public class AmazonBedrockControlle {

    @Autowired
    private AmazonBedrockService amazonBedrockService;

    @PostMapping("/")
    public String getPrompt(){
        String generedText = amazonBedrockService.getPrompt();
        
        System.out.println("Generated text: " + generedText);

        return generedText;
    }
    
}
