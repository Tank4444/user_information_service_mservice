package ru.chuikov.mservice.user_information_service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.chuikov.mservice.user_information_service.entity.Role;
import ru.chuikov.mservice.user_information_service.entity.Status;
import ru.chuikov.mservice.user_information_service.entity.User;
import ru.chuikov.mservice.user_information_service.repository.UserRepository;
import ru.chuikov.mservice.user_information_service.service.UserService;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s).map( user ->
                        org.springframework.security.core.userdetails.User.withUsername(
                                user.getUsername()).
                                password(user.getPassword()).
                                authorities(new SimpleGrantedAuthority("ROLE_"+user.getRole())).build()
                ).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }

    @Override
    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void add(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(Status.OPEN);
        user.setRole(Role.USER);
        userRepository.saveAndFlush(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }
}
