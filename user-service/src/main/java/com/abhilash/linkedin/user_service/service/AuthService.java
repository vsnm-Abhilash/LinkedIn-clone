package com.abhilash.linkedin.user_service.service;

import com.abhilash.linkedin.user_service.dto.LoginRequestDto;
import com.abhilash.linkedin.user_service.dto.SignUpRequestDto;
import com.abhilash.linkedin.user_service.dto.UserDto;
import com.abhilash.linkedin.user_service.entities.User;
import com.abhilash.linkedin.user_service.exception.BadCredentialsException;
import com.abhilash.linkedin.user_service.exception.RuntimeConflictException;
import com.abhilash.linkedin.user_service.repsitory.UserRepository;
import com.abhilash.linkedin.user_service.utils.PasswordUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final JWTService jwtService;
    public UserDto signUp(SignUpRequestDto signUpRequestDto) {
        User user=userRepository.findByEmail(signUpRequestDto.getEmail()).orElse(null);
        if(user!=null){
            throw new RuntimeConflictException("Cannot signup, User already exists with email "+signUpRequestDto.getEmail());
        }
        User mappedUser=mapper.map(signUpRequestDto,User.class);
        mappedUser.setPassword(PasswordUtil.hashPassword(signUpRequestDto.getPassword()));

        User savedUser=userRepository.save(mappedUser);
        return mapper.map(savedUser, UserDto.class);
    }

    public String login(LoginRequestDto loginRequestDto) {
        User user=userRepository.findByEmail(loginRequestDto.getEmail()).orElseThrow(
                ()->new RuntimeConflictException("User not found with email "+loginRequestDto.getEmail())
        );
        boolean isPasswordMatched=PasswordUtil.checkPassword(loginRequestDto.getPassword(),user.getPassword());
        if(!isPasswordMatched){
            throw new BadCredentialsException("Incorrect password");
        }

        return jwtService.generateAccessToken(user);

    }
}
