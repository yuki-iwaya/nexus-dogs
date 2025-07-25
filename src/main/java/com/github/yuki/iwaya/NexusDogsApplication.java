package com.github.yuki.iwaya;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
@SpringBootApplication
public class NexusDogsApplication {

	public static void main(String[] args) {
		SpringApplication.run(NexusDogsApplication.class, args);
	}

	@GetMapping("/hello")
	public ResponseEntity<String> hello(){
		return new ResponseEntity<String>("hello", new HttpHeaders(), HttpStatus.OK);
	}

}
