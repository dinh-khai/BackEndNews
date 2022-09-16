package com.news.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtFilter extends OncePerRequestFilter{
	@Autowired
	JwtProvider token;
	@Autowired
	UserDetailsService userService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		try {
			String jwt = getJwtFromRequest(request);
			if (StringUtils.hasText(jwt) && token.validateToken(jwt)) {
				String userName = token.getUserNameFromJWT(jwt);

				UserDetails userDetails = userService.loadUserByUsername(userName);
				if (userDetails != null) {
					UsernamePasswordAuthenticationToken authen = new UsernamePasswordAuthenticationToken(userDetails,
							null, userDetails.getAuthorities());
					authen.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

					SecurityContextHolder.getContext().setAuthentication(authen);
				}

			}
		} catch (Exception e) {
			System.out.println("err");
		}
		
		filterChain.doFilter(request, response);

	}
	
	public String getJwtFromRequest(HttpServletRequest request) {
		String breakToken = request.getHeader("Authorization");
		if (StringUtils.hasText(breakToken) && breakToken.startsWith("Bearer ")) {
			 return breakToken.replace("Bearer ", "");
		}
		return null;
	}
}
