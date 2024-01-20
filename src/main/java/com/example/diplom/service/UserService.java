package com.example.diplom.service;

import com.example.diplom.entity.User;
import com.example.diplom.form.UserRegistrationForm;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {

    boolean register(UserRegistrationForm userRegistrationForm);

    Iterable<User> findUsersByRole(String role);

    Optional<User> findUserByLogin(String login);

    Optional<User> findById(Integer id);
}
