package ru.kata.spring.boot_security.demo.controller;

import java.security.Principal;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@RestController
public class RestUserController {

 private final UserService userService;

  public RestUserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/user")
  public ResponseEntity <User> infoAboutUser(Principal principal) {
    userService.findByName(principal.getName());
    return new ResponseEntity<>(userService.findByName(principal.getName()), HttpStatus.OK);
  }
}





