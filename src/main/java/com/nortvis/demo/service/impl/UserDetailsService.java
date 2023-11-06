package com.nortvis.demo.service.impl;


import com.nortvis.demo.entity.User;
import com.nortvis.demo.exception.InvalidUserException;
import com.nortvis.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final UserRepository userRepository;
    private Optional<User> userFromDatabase;

    @Override
    @SneakyThrows
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        org.springframework.security.core.userdetails.User user = null;
        userFromDatabase = userRepository.findByUserName(userName);
        if (userFromDatabase.isPresent()) {
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            user = new org.springframework.security.core.userdetails.User(userFromDatabase.get().getUserName(), userFromDatabase.get().getPassword(), grantedAuthorities);
        } else {
            throw new InvalidUserException("User doesn't exist");
        }
        return user;
    }
}
