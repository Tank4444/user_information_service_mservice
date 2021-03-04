package ru.chuikov.mservice.user_information_service.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Username is taken")
public class UsernameIsTaken extends Exception {
}
