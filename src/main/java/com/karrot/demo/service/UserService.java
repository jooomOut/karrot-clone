package com.karrot.demo.service;

import com.karrot.demo.domain.user.User;
import com.karrot.demo.domain.user.UserRepository;
import com.karrot.demo.exception.DuplicateUserException;
import com.karrot.demo.web.dto.user.RegisterUserDto;
import com.karrot.demo.web.dto.user.UserSessionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(RegisterUserDto userDto){
        if (userRepository.findByPhone(userDto.getPhone()).isPresent()){ // 전화번호 중복 검출
            throw new DuplicateUserException();
        }
        try {
            userRepository.save(toEntity(userDto));
        } catch(DataIntegrityViolationException e){ // 무결성 조건 (중복) - 이메일
            throw new DuplicateUserException();
        }
    }

    public UserSessionDto login(String email, String password){
        User userEntity = userRepository.findByEmail(email)
                .filter(user -> passwordEncoder.matches(password, user.getPassword()))
                .orElse(null);
        return userEntity == null ? null : toUserSessionDto(userEntity);
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
    public UserSessionDto toUserSessionDto(User user){
        return UserSessionDto.builder()
                .email(user.getEmail())
                .username(user.getUsername())
                .phone(user.getPhone())
                .nickname(user.getNickname())
                .build();
    }

}
