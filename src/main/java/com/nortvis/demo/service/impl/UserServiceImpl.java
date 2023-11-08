package com.nortvis.demo.service.impl;

import com.nortvis.demo.dto.CreateUserDto;
import com.nortvis.demo.dto.UserDto;
import com.nortvis.demo.entity.User;
import com.nortvis.demo.exception.NotFoundException;
import com.nortvis.demo.repository.UserRepository;
import com.nortvis.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.nortvis.demo.service.mapper.MapperUtility.toObject;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserDto getUser(Long userId) {
        log.info("Request received to fetch user by id: {}", userId);
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty())
            throw new NotFoundException(String.format("user with id %s does not exists", userId));
        UserDto userDto = toObject(user.get(), UserDto.class);
        log.info("User details fetched for the id: {}", userId);
        return userDto;
    }

    @Override
    public UserDto getUser(String userName) {
        log.info("Request received to fetch user by userName: {}", userName);
        UserDto userDto = toObject(getUserEntity(userName), UserDto.class);
        log.info("User details fetched for the userName: {}", userName);
        return userDto;
    }

    @Override
    public UserDto saveUser(CreateUserDto createUserDto) {
        log.info("Request received to create new user with username: {}", createUserDto.getUserName());
        // Todo; check unique constraint error for duplicate user
        User user = userRepository.save(toObject(createUserDto, User.class));
        log.info("New user created with username: {}", createUserDto.getUserName());
        return toObject(user, UserDto.class);
    }

    @Override
    public User getUserEntity(String userName) {
        log.info("Reading user entity with username: {}",userName);
        Optional<User> user = userRepository.findByUserName(userName);
        if (user.isEmpty())
            throw new NotFoundException(String.format("user with userName %s does not exists", userName));
        return user.get();
    }

    @Override
    public User updateUser(User user) {
        log.info("Updating user with id: {}", user.getId());
        return userRepository.save(user);
    }

    @Override
    public String getLoggedInUserName() {
        log.info("Invoked getLoggedInUser service");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new NotFoundException("Invalid User");
        }
        Object principal = authentication.getPrincipal();
        String username = null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        log.info("Exiting getLoggedInUser service");
        return  username;
    }
}
