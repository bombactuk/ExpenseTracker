package com.expense.tracker.service;



import com.expense.tracker.dto.RegistrationUserDto;
import com.expense.tracker.entity.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findByUsername(String username);

    User createNewUser(RegistrationUserDto registrationUserDto);

}
