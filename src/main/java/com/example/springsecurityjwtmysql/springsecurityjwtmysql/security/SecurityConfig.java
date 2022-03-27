package com.example.springsecurityjwtmysql.springsecurityjwtmysql.security;

import com.example.springsecurityjwtmysql.springsecurityjwtmysql.filter.CustomAuthenticationFilter;
import com.example.springsecurityjwtmysql.springsecurityjwtmysql.filter.CustomAuthorizationFilter;
import com.example.springsecurityjwtmysql.springsecurityjwtmysql.service.CusotmUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CusotmUserDetailService cusotmUserDetailService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(cusotmUserDetailService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/api/login");
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers("/api/login/**", "/api/token/refresh/**").permitAll();
        http.authorizeRequests().antMatchers("/api/user/**").hasAnyAuthority("ROLE_USER");
        http.authorizeRequests().antMatchers("/api/user/save/**").hasAnyAuthority("ROLE_AMIN");
        http.authorizeRequests().anyRequest().permitAll();
        http.addFilter(customAuthenticationFilter);
//        http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean()));
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
