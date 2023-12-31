package ru.kata.spring.boot_security.demo.service;

import java.util.List;
import ru.kata.spring.boot_security.demo.model.User;

public interface UserService {

  void addUser(User user);

  void deleteUser(int id);

  void updateUser( User user);

  User showUser(int id);

  User findByName(String username);

  List<User> listUsers();
}
