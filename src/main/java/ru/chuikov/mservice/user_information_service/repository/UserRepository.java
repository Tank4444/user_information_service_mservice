package ru.chuikov.mservice.user_information_service.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.chuikov.mservice.user_information_service.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends BasicRepository<User,Long> {

    @Query("select u from User u where lower(u.username) like lower(:username) ")
    Optional<User> findByUsername(@Param("username") String username);

    @Query("select u from User u where lower(u.email) like lower(:email) ")
    Optional<User> findByEmail(@Param("email") String email);
}
