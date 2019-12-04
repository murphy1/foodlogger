package com.murphy1.foodlogger.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService){
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeRequests().antMatchers("/api/logger/products").hasAnyAuthority("USER", "TEST")
                .antMatchers("/add_food").hasAnyAuthority("USER", "TEST")
                .antMatchers("/api/logger/products/*").hasAnyAuthority("USER", "TEST")
                .antMatchers("/logger/product_query").hasAnyAuthority("USER", "TEST")
                .antMatchers("/logger/product_query/detailed_query/*").hasAnyAuthority("USER", "TEST")
                .antMatchers("/nutritionix").hasAnyAuthority("USER", "TEST")
                .antMatchers("/profile").hasAnyAuthority("USER", "TEST")
                .antMatchers("/remove_food/*").hasAnyAuthority("USER", "TEST")
                .antMatchers("/update/goals/daily").hasAnyAuthority("USER", "TEST")
                .antMatchers("/update/goals/daily/save").hasAnyAuthority("USER", "TEST")
                .antMatchers("/view/analytics").hasAnyAuthority("USER", "TEST")
                .antMatchers("/").permitAll()
                .and().formLogin().loginPage("/login");

        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
