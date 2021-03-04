package ru.chuikov.mservice.user_information_service.controller;

import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/*
    Класс-контроллер служайщий для тестирования доступа к сервису
 */
@RestController
@RequestMapping("/test")
@Log
public class TestController {

    @GetMapping("/open")
    public Map<?,?> helloGet(){
        Map<String,String> map= new HashMap<>();
        map.put("Status","OK");
        log.info("test get no auth request is OK");
        return map;
    }


    @PostMapping("/open")
    public Map<?,?> helloPost(){
        Map<String,String> map= new HashMap<>();
        map.put("Status","OK");
        log.info("test post no auth request is OK");
        return map;
    }

    @GetMapping("/auth")
    public Map<?,?> helloAuthGet(){
        Map<String,String> map= new HashMap<>();
        map.put("Status","OK");
        log.info("test get auth request is OK");
        return map;
    }

    @PostMapping("/auth")
    public Map<?,?> helloAuthPost(){
        Map<String,String> map= new HashMap<>();
        map.put("Status","OK");
        log.info("test post auth request is OK");
        return map;
    }
}
