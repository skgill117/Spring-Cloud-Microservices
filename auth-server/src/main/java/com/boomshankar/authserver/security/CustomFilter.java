package com.boomshankar.authserver.security;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class CustomFilter implements Filter {

	@Override
	public void destroy() {
		System.out.println("------------- filter destroy called");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
			throws IOException, ServletException {
		System.out.println("------------- do filter called");
		HttpServletRequest request = (HttpServletRequest) req;
		Principal userPrincipal = request.getUserPrincipal();
		System.out.println("user principal ::--"+userPrincipal);
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
		System.out.println("------------- filter init called");
	}

}
