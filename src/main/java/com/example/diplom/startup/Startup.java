package com.example.diplom.startup;

import com.example.diplom.form.UserRegistrationForm;
import com.example.diplom.service.UserService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class Startup {

    private final UserService userService;

    public Startup(UserService userService) {
        this.userService = userService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {

        if (userService.findUsersByRole("ADMIN").iterator().hasNext()) {
            System.out.println("Superuser is exists");
            return;
        }
        UserRegistrationForm superUserForm = new UserRegistrationForm();
        superUserForm.setLogin("admin");
        superUserForm.setPassword("qwerty");
        superUserForm.setPasswordConfirmation("qwerty");
        superUserForm.setRole("ADMIN");
        if (userService.register(superUserForm)) {
            System.out.println("Created default superuser");
        } else {
            System.out.println("Can`t create default superuser");
        }
    }
}