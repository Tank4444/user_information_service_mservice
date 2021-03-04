package ru.chuikov.mservice.user_information_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
@NoRepositoryBean
public interface BasicRepository<T,ID extends Serializable> extends JpaRepository<T,ID> {
}
