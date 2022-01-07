package com.karrot.demo.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web){
        web.ignoring().antMatchers("/img/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http
          .authorizeRequests()
              .antMatchers("/login", "/register").permitAll()
              .anyRequest().authenticated()
          .and()
              .formLogin()
              .loginPage("/main") // 기본 로그인 페이지 변경
              .defaultSuccessUrl("/")
              .permitAll()
          .and()
              .logout()
              .logoutSuccessUrl("/")
          ;
}

}
