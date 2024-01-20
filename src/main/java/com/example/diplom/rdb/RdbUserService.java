package com.example.diplom.rdb;

import com.example.diplom.entity.User;
import com.example.diplom.form.UserRegistrationForm;
import com.example.diplom.rdb.repository.UserRepository;
import com.example.diplom.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RdbUserService implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RdbUserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean register(UserRegistrationForm userRegistrationForm) {
        if (!userRegistrationForm.getPassword().equals(userRegistrationForm.getPasswordConfirmation())) {
            return false;
        }
        if (userRepository.findByLogin(userRegistrationForm.getLogin()).isPresent()) {
            return false;
        }
        User user = new User();
        user.setLogin(userRegistrationForm.getLogin());
        String passwordHash = passwordEncoder.encode(userRegistrationForm.getPassword());
        user.setPassword(passwordHash);
        user.setRole(userRegistrationForm.getRole());
        userRepository.save(user);
        return true;
    }

    @Override
    public Iterable<User> findUsersByRole(String role) {
        return userRepository.findByRole(role);
    }

    @Override
    public Optional<User> findUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

}
