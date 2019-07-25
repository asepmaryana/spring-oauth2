package com.codeaches.oauth.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {
	
	@Autowired
	DataSource ds;
	
	@Autowired
	AuthenticationManager authMgr;
	
	@Autowired
	private UserDetailsService usrSvc;
	
	@Bean
	public TokenStore tokenStore() {
		return new JdbcTokenStore(ds);
	}
	
	@Bean("clientPasswordEncoder")
	PasswordEncoder clientPasswordEncoder() {
		return new BCryptPasswordEncoder(8);
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer auth) throws Exception {
		// TODO Auto-generated method stub
		//super.configure(security);
		
		// This will enable /oauth/check_token access
		auth.checkTokenAccess("permitAll()");
		
		// BCryptPasswordEncoder(8) is used for oauth_client_details.user_secret
		auth.passwordEncoder(clientPasswordEncoder());
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		// TODO Auto-generated method stub
		//super.configure(clients);
		
		clients.jdbc(ds);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		// TODO Auto-generated method stub
		//super.configure(endpoints);
		
		endpoints.tokenStore(tokenStore());
		endpoints.authenticationManager(authMgr);
		endpoints.userDetailsService(usrSvc);
	}
	
	
}
