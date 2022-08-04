package com.news.config;

//import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.transaction.annotation.Transactional;

//import org.springframework.context.annotation.Configuration;


//@SuppressWarnings("deprecation")
//@Configuration
//@EnableWebSecurity
//@Transactional
//public class SecurityConfig extends WebSecurityConfigurerAdapter{
//	@Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
////        .csrf().disable()   
//        .authorizeRequests()
//        		.antMatchers("/","/news/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//            //.formLogin().and()
//            .httpBasic();
//    }
//}
