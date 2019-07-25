package com.codeaches.oauth.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	@GetMapping("/test")
	public @ResponseBody List<String> test() {
		return Arrays.asList("Hello", "World", "From", "Public");
	}
	
}
