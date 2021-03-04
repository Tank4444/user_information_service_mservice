package ru.chuikov.mservice.user_information_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
Это начало программы
 */
@SpringBootApplication
public class UserInformationServiceApplication {

    //Здесь запускаем приложение и передаём в него класс и аргументы
    public static void main(String[] args) {
        SpringApplication.run(UserInformationServiceApplication.class, args);
    }

}
