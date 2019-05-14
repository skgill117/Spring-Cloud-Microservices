package com.boomshankar.authserver.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		       httpSecurity.authorizeRequests()
				// .antMatchers("**/*")
				.anyRequest().permitAll().and()
				.addFilterBefore(customFilter(), BasicAuthenticationFilter.class)
				.httpBasic();
		        httpSecurity.csrf().disable();

	}

	
	@Bean
	public CustomFilter customFilter() {
		return new CustomFilter();
	}

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		 auth.inMemoryAuthentication()
		.withUser("shiv").password("password").roles("ÄDMIN").and()
		.withUser("rohit").password("password").roles("ADMIN","USER");
	}

}
