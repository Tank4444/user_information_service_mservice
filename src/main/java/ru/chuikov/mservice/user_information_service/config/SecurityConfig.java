package ru.chuikov.mservice.user_information_service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

/*
    Класс безопастности веб приложения
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //Имя пользователя для доступа к этому приложению
    @Value("${access.username}")
    private String username;

    //Пароль доступа к этому приложению
    @Value("${access.password}")
    private String password;

    //Роль пользователя этого приложения
    @Value("${access.authorities}")
    private String authorities;

    //Подключаем кодировщик паролей
    @Autowired
    public PasswordEncoder passwordEncoder;


    /*
        Функция, в которой задаётся пользователь
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser(username).password(passwordEncoder.encode(password)).authorities(new SimpleGrantedAuthority(authorities));
    }

    /*
        Функция, в которой задются маршруты и доступ к ним
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .mvcMatchers("/","").permitAll()
                .mvcMatchers("/user").authenticated()
                .mvcMatchers("/test/auth").authenticated()
                .mvcMatchers("/user/add").permitAll()
                .and().httpBasic()
                .and().sessionManagement().disable();
    }
}
