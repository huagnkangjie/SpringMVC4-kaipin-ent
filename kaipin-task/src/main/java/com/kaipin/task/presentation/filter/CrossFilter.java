package com.kaipin.task.presentation.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class CrossFilter extends OncePerRequestFilter  {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
	 
            // CORS "pre-flight" request
			  response.setHeader("Access-Control-Allow-Origin", "http://kaipin.com:8083");
			    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
			    response.setHeader("Access-Control-Max-Age", "3600");
			  // response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
			   response.setHeader("Access-Control-Allow-Credentials", "true");
    
        filterChain.doFilter(request, response);
		
	}

}
