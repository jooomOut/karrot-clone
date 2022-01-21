package com.karrot.demo.service;

import com.karrot.demo.domain.user.Account;
import com.karrot.demo.domain.user.UserRepository;
import com.karrot.demo.exception.DuplicateUserException;
import com.karrot.demo.web.dto.user.RegisterUserDto;
import com.karrot.demo.web.dto.user.UserSessionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;

@Slf4j
@Service
public class UserService {

    private ImageService imageService;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                       ImageService imageService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.imageService = imageService;
    }

    public UserSessionDto findUserBy(Long userId){
        Account user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("user not found with id" + userId));
        return toDto(user);
    }

    public void registerUser(RegisterUserDto userDto){
        if (userRepository.findByPhone(userDto.getPhone()).isPresent()){ // 전화번호 중복 검출
            throw new DuplicateUserException();
        }
        try {
            Account user = toEntity(userDto);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        } catch(DataIntegrityViolationException e){ // 무결성 조건 (중복) - 이메일
            throw new DuplicateUserException();
        }
    }

    public void updateProfile(Long userId, MultipartFile image, String nickname){
        if (nickname == null || nickname.equals("")){
            throw new IllegalArgumentException("닉네임은 빈 값일 수 없습니다.");
        }
        Account user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("user not found with id" + userId));
        user.setNickname(nickname);
        userRepository.save(user);
        //TODO: 이미지 처리하기
        try {
            imageService.upload(user, image);
        } catch (IOException e){
            log.info("이미지 저장 실패 - user id : " + userId);
        }
    }

    public Account toEntity(RegisterUserDto userDto){
        return Account.builder().
                email(userDto.getEmail())
                .password(userDto.getPassword())
                .username(userDto.getUsername())
                .phone(userDto.getPhone())
                .nickname(userDto.getNickname())
                .build();
    }

    private UserSessionDto toDto(Account user) {
        return UserSessionDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .phone(user.getPhone())
                .username(user.getUsername())
                .image(user.getImage())
                .build();
    }
}
