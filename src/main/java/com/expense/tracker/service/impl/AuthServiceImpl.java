package com.expense.tracker.service.impl;



import com.expense.tracker.dto.RegistrationUserDto;
import com.expense.tracker.dto.UserDto;
import com.expense.tracker.dto.request.JwtRequestDto;
import com.expense.tracker.dto.response.JwtResponseDto;
import com.expense.tracker.entity.User;
import com.expense.tracker.service.AuthService;
import com.expense.tracker.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


import static com.expense.tracker.exception.ServiceClientExceptionHolder.INVALID_LOGIN_OR_PASSWORD;
import static com.expense.tracker.exception.ServiceClientExceptionHolder.PASSWORD_MISMATCH;
import static com.expense.tracker.exception.ServiceClientExceptionHolder.USER_EXISTS;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserServiceImpl userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    @Override
    public ResponseEntity<?> createAuthToken(JwtRequestDto authRequest) {
        log.info("Attempting to authenticate user: {}", authRequest.getUsername());

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
            log.info("User '{}' authenticated successfully", authRequest.getUsername());
        } catch (BadCredentialsException e) {
            log.warn("Authentication failed for user '{}': incorrect credentials", authRequest.getUsername());
            throw INVALID_LOGIN_OR_PASSWORD.getException();
        }

        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);

        log.debug("Generated JWT token for user '{}'", authRequest.getUsername());

        return ResponseEntity.ok(new JwtResponseDto(token));
    }

    @Override
    public ResponseEntity<?> createNewUser(RegistrationUserDto registrationUserDto) {
        log.info("Registering new user: {}", registrationUserDto.getUsername());

        if (!registrationUserDto.getPassword().equals(registrationUserDto.getConfirmPassword())) {
            log.warn("Password mismatch for user '{}'", registrationUserDto.getUsername());
            throw PASSWORD_MISMATCH.getException();
        }

        if (userService.findByUsername(registrationUserDto.getUsername()).isPresent()) {
            log.warn("User '{}' already exists", registrationUserDto.getUsername());
            throw USER_EXISTS.getException();
        }

        User user = userService.createNewUser(registrationUserDto);
        log.info("User '{}' registered successfully", registrationUserDto.getUsername());

        return ResponseEntity.ok(new UserDto(user.getId(), user.getUsername(), user.getEmail()));
    }

}