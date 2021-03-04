package ru.chuikov.mservice.user_information_service.service;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.chuikov.mservice.user_information_service.entity.User;

import java.util.Optional;

public interface UserService extends BasicService<User>,UserDetailsService {

    Optional<User> getUserByUsername(String username);

    Optional<User> getUserByEmail(String email);
}
