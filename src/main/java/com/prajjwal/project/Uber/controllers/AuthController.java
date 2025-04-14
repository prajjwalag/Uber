package com.prajjwal.project.Uber.controllers;


import com.prajjwal.project.Uber.dtos.SignUpDTO;
import com.prajjwal.project.Uber.dtos.UserDTO;
import com.prajjwal.project.Uber.services.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    UserDTO signUp(@RequestBody SignUpDTO signUpDTO) {

        return authService.signup(signUpDTO);

    }
}
