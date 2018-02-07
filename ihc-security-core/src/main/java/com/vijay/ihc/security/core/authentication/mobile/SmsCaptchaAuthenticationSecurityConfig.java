package com.vijay.ihc.security.core.authentication.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class SmsCaptchaAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {


    @Autowired
    private AuthenticationSuccessHandler ihcAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler ihcAuthenticationFailureHandler;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        SmsCaptchaAuthenticationFilter smsCaptchaAuthenticationFilter = new SmsCaptchaAuthenticationFilter();

        smsCaptchaAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));

        smsCaptchaAuthenticationFilter.setAuthenticationSuccessHandler(ihcAuthenticationSuccessHandler);
        smsCaptchaAuthenticationFilter.setAuthenticationFailureHandler(ihcAuthenticationFailureHandler);

        SmsCaptchaAuthenticationProvider smsCaptchaAuthenticationProvider = new SmsCaptchaAuthenticationProvider();
        smsCaptchaAuthenticationProvider.setUserDetailsService(userDetailsService);

        http.authenticationProvider(smsCaptchaAuthenticationProvider)
            .addFilterAfter(smsCaptchaAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
