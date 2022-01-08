package com.karrot.demo.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    public void configure(WebSecurity web){
        web.ignoring().antMatchers("/img/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http
          .authorizeRequests()
              .antMatchers("/login", "/user/register").permitAll()
              .antMatchers(HttpMethod.POST, "/users").permitAll() /*회원가입 요청 허용*/
              .antMatchers(HttpMethod.POST, "/users/login").permitAll() /*회원가입 요청 허용*/
              .anyRequest().authenticated()
          .and()
              .formLogin()
              .loginPage("/main") // 기본 로그인 페이지 변경
              .usernameParameter("email")
              .passwordParameter("password")
              .loginProcessingUrl("/users/login")
              .defaultSuccessUrl("/items")
              .successHandler(new AuthenticationSuccessHandler() {
                  @Override
                  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                      System.out.println(">>>>>> success handler");
                      response.sendRedirect("/");
                  }
              })
              .failureHandler(new AuthenticationFailureHandler() {
                  @Override
                  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                      System.out.println(">>>>>> fail handler");
                      response.sendRedirect("/");
                  }
              })
              .permitAll()
          .and()
              .logout()
              .logoutSuccessUrl("/")
          .and().csrf().disable()
          ;
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
