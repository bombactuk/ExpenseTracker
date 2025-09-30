package com.expense.tracker.service;



import com.expense.tracker.dto.RegistrationUserDto;
import com.expense.tracker.dto.request.JwtRequestDto;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<?> createAuthToken(JwtRequestDto authRequest);

    ResponseEntity<?> createNewUser(RegistrationUserDto registrationUserDto);

}
