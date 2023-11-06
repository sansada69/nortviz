package com.nortvis.demo.configuration;

import com.nortvis.demo.entity.User;
import com.nortvis.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import static com.nortvis.demo.constants.ApiConstants.ADMIN;

@Slf4j
@Component
@RequiredArgsConstructor
public class SuperAdminInitializer implements CommandLineRunner {
    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        User admin = new User();
        admin.setUserName(ADMIN);
        admin.setPassword(ADMIN);
        admin.setName(ADMIN);
        userRepository.save(admin);
        log.info("Admin user created ...");
    }
}
