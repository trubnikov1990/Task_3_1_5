package ru.kata.spring.boot_security.demo.controller;

import java.security.Principal;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

@RestController
@RequestMapping("/admin")
public class RestAdminController {

  private final UserService userService;
  private final RoleService roleService;

  @Autowired
  public RestAdminController(UserService userService, RoleService roleService) {
    this.userService = userService;
    this.roleService = roleService;
  }

  @GetMapping("/user")
  public ResponseEntity <User> showInfoAdmin(Principal principal) {
    return new ResponseEntity<>(userService.findByName(principal.getName()), HttpStatus.OK);
  }

  @GetMapping("/users")
  public ResponseEntity <List <User>> getAllUsers() {
    return new ResponseEntity<>(userService.listUsers(), HttpStatus.OK);
  }

  @GetMapping("/role/{id}")
  public ResponseEntity <List <Role>> getRole(@PathVariable("id") int id) {
    return new ResponseEntity<>(userService.showUser(id).getRoleList(), HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity <List <Role>> getAllRole() {
    return new ResponseEntity<>(roleService.getAllRoles(), HttpStatus.OK);
  }

  @GetMapping("/user/{id}")
  public ResponseEntity<User> getUserById(@PathVariable("id") int id) {
    return new ResponseEntity<>(userService.showUser(id), HttpStatus.OK);
  }


  @PostMapping()
  public ResponseEntity <User> saveNewUser(@RequestBody @Valid User user) {
    userService.addUser(user);
    return new ResponseEntity<>(user , HttpStatus.OK);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<User> updateUser(@RequestBody User user) {
    userService.updateUser(user);
    return new ResponseEntity<>(user, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") int id) {
    userService.deleteUser(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}


