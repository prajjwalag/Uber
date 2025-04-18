package com.prajjwal.project.Uber.services.impl;

import com.prajjwal.project.Uber.dtos.DriverDTO;
import com.prajjwal.project.Uber.dtos.SignUpDTO;
import com.prajjwal.project.Uber.dtos.UserDTO;
import com.prajjwal.project.Uber.entities.Driver;
import com.prajjwal.project.Uber.entities.User;
import com.prajjwal.project.Uber.entities.enums.Role;
import com.prajjwal.project.Uber.exceptions.ResourceNotFoundException;
import com.prajjwal.project.Uber.exceptions.RuntimeConflictException;
import com.prajjwal.project.Uber.repositories.UserRepository;
import com.prajjwal.project.Uber.services.AuthService;
import com.prajjwal.project.Uber.services.DriverService;
import com.prajjwal.project.Uber.services.RiderService;
import com.prajjwal.project.Uber.services.WalletService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RiderService riderService;
    private final ModelMapper modelMapper;
    private final WalletService walletService;
    private final DriverService driverService;

    @Override
    public String login(String email, String password) {
        return "";
    }

    @Override
    @Transactional
    public UserDTO signup(SignUpDTO signUpDTO) {
        User user = userRepository.findByEmail(signUpDTO.getEmail()).orElse(null);

        if(user!=null) {
            throw new RuntimeConflictException("Cannot signup, User already exist with email " + signUpDTO.getEmail());
        }

        User mappedUser = modelMapper.map(signUpDTO, User.class);

        mappedUser.setRoles(Set.of(Role.RIDER));

        User savedUser = userRepository.save(mappedUser);

        riderService.createNewRider(savedUser);
        walletService.createNewWallet(savedUser);

        return modelMapper.map(savedUser, UserDTO.class);
    }

    @Override
    public DriverDTO onboardNewDriver(Long userId, String vehicleId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User does not exist with user id:" + userId));

        if(user.getRoles().contains(Role.DRIVER)) {
            throw new RuntimeConflictException("User with id: " + userId + " has already Driver role");
        }

        Driver createDriver = Driver.builder()
                .user(user)
                .rating(0.0)
                .vehicleId(vehicleId)
                .available(true)
                .build();

        user.getRoles().add(Role.DRIVER);
        userRepository.save(user);
        Driver savedDriver = driverService.createNewDriver(createDriver);
        return modelMapper.map(savedDriver, DriverDTO.class);
    }
}
