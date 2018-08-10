package com.alex.test.services;

import com.alex.test.model.Role;
import com.alex.test.model.User;
import com.alex.test.repository.UserRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collections;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void init() {
        userRepository.save(User.builder()
                .username("user")
                .password("password")
                .authorities(Collections.singleton(Role.USER))
                .accountNonExpired(false)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .build());
    }

    @Override
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username) != null ? userRepository.findUserByUsername(username) :
                (UserDetails) new UsernameNotFoundException("user " + username + " was not found!");
    }
}
