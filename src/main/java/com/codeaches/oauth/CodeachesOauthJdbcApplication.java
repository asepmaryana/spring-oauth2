package com.codeaches.oauth;

import java.util.Base64;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class CodeachesOauthJdbcApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodeachesOauthJdbcApplication.class, args);
		
		String client = "damri";
		String secret = "damri123";
		String plain  = client+":"+secret;
		String basic = Base64.getEncoder().encodeToString(plain.getBytes());
		System.out.println(plain+ " => "+basic);
		
		PasswordEncoder encoderOauth = new BCryptPasswordEncoder(8);
		System.out.println(secret+ " => "+encoderOauth.encode(secret));
	}

}
