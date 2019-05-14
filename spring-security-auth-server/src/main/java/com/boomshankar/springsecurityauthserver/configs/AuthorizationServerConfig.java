package com.boomshankar.springsecurityauthserver.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@EnableAuthorizationServer
@Configuration
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	
	public void configurer(AuthorizationServerSecurityConfigurer security){
		
		security.tokenKeyAccess("permitAll()")
		.checkTokenAccess("isAuthenticated()");
		//12:30
	}

}
