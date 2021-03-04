package ru.chuikov.mservice.user_information_service.service;

import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

public interface BasicService<T> {
    Optional<T> getById(Long id);
    void add(T t);
    void delete(T t);
}
