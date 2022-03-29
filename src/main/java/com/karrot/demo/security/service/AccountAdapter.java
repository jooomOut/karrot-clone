package com.karrot.demo.security.service;

import com.karrot.demo.domain.user.Account;
import com.karrot.demo.web.dto.user.UserSessionDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class AccountAdapter extends User {
    //private final Account account;
    private final UserSessionDto user;

    public AccountAdapter(Account account, Collection<? extends GrantedAuthority> authorities) {
        super(account.getEmail(), account.getPassword(), authorities);
        //this.account = account;
        this.user = UserSessionDto.from(account);
    }

    /*public Account getAccount(){
        return this.account;
    }*/
    public UserSessionDto getUser(){
        return this.user;
    }
}
