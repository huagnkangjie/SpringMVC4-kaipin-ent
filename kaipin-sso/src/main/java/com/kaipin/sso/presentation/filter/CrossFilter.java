package com.kaipin.sso.presentation.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import com.kaipin.sso.util.PropUtil;

public class CrossFilter extends OncePerRequestFilter  {
	
	public static String PRO_FILE = "sso.properties";
	public static String SSO_DOMAIN = "sso.domain";
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		PropUtil pro = new PropUtil(PRO_FILE);
		
        // CORS "pre-flight" request
		response.setHeader("Access-Control-Allow-Origin", pro.getValue(SSO_DOMAIN));
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		// response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
		response.setHeader("Access-Control-Allow-Credentials", "true");
    
        filterChain.doFilter(request, response);
		
	}

}
