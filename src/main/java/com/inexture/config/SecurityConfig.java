package com.inexture.config;

import com.inexture.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SuppressWarnings("ALL")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/signin").permitAll()
                .antMatchers("/public/**").hasRole("User")
                .antMatchers("/user/**").hasRole("Admin")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/signin")
                .loginProcessingUrl("/dologin")
                .usernameParameter("email")
                .passwordParameter("password")
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(customUserDetailsService);
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .withUser("jay@gmail.com")
//                .password(this.passwordEncoder().encode("123"))
//                .roles("Admin");
//
//        auth
//                .inMemoryAuthentication()
//                .withUser("jaydeep@gmail.com")
//                .password(this.passwordEncoder().encode("123"))
//                .roles("User");
//    }

//    @Bean
//    public PasswordEncoder passwordEncoder()
//    {
//        return NoOpPasswordEncoder.getInstance();
//    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        System.out.println("ignore");
        //Web resources
        web.ignoring().antMatchers("/resources/css/**");
        web.ignoring().antMatchers("/resources/js/**");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
