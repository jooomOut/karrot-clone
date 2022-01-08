package com.karrot.demo.service;

import com.karrot.demo.domain.user.User;
import com.karrot.demo.domain.user.UserRepository;
import com.karrot.demo.exception.DuplicateUserException;
import com.karrot.demo.web.dto.user.RegisterUserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void registerUser(RegisterUserDto userDto){
        try {
            userRepository.save(toEntity(userDto));
        } catch(DuplicateKeyException e){
            throw new DuplicateUserException();
        }
    }
    public User toEntity(RegisterUserDto userDto){
        return User.builder().
                email(userDto.getEmail())
                .password(userDto.getPassword())
                .username(userDto.getUsername())
                .phone(userDto.getPhone())
                .nickname(userDto.getNickname())
                .build();
    }
}
