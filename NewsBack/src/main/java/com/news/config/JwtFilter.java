package com.news.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.news.common.Constants;
import com.news.exception.MyException;
import com.news.exception.customException.FileException;

public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    JwtProvider token;
    @Autowired
    UserDetailsService userService;

    /**
     * do fillter internal
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
                                    throws ServletException, IOException {
        String jwt = getJwtFromRequest(request);
        if (!StringUtils.hasText(jwt) || !token.validateToken(jwt)) {
            filterChain.doFilter(request, response);
            return;
        }

        String userName = token.getUserNameFromJWT(jwt);
        UserDetails userDetails = userService.loadUserByUsername(userName);
        if (userDetails != null) {
            UsernamePasswordAuthenticationToken authen = new UsernamePasswordAuthenticationToken
                    (userDetails,
                            null,
                            userDetails.getAuthorities());
            authen.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            
            SecurityContextHolder.getContext().setAuthentication(authen);
        }
        filterChain.doFilter(request, response);
    }

    /**
     * get jwt in request
     * 
     * @param request
     * @return
     */
    public String getJwtFromRequest(HttpServletRequest request) {
        String breakToken = request.getHeader("Authorization");
        if (StringUtils.hasText(breakToken) && breakToken.startsWith("Bearer ")) {
            return breakToken.replace("Bearer ", "");
        }
        return null;
    }
}
