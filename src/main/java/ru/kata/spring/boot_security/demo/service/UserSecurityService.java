package ru.kata.spring.boot_security.demo.service;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

@Service
public class UserSecurityService implements UserDetailsService {

  private final UserRepository userRepository;

  @Autowired
  public UserSecurityService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }



  @Override
  @Transactional
  public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
    User user = userRepository.findByName(name);
    if (user == null) {
      throw new UsernameNotFoundException("User not found");
    }
    return new org.springframework.security.core.userdetails.User(user.getUsername(),
        user.getPassword(), user.getAuthorities());
  }
}
