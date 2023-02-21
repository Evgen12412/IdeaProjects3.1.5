package ru.kata.spring.boot_security.demo.service.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImp implements UserServiceInterface {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImp(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public List<User> allUsers() {
        log.info("All users");
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        log.info("Saving user {}", user);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User findUserById(Long userid) {
        Optional<User> user = userRepository.findById(userid);
        return user.orElse(new User());
    }

    @Override
    @Transactional
    public void deleteUser(Long userId) {
        log.info("Deleting user {}", userId);
        userRepository.deleteById(userId);
    }

//    @Override
//    @Transactional
//    public void update(Long id, User user) {
//        log.info("Updating user {}", user);
//        user.setId(id);
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        userRepository.save(user);
//    }

    @Override
    public boolean checkLogin(User user) {
        return userRepository.findByUsername(user.getUsername()) != null;
    }

    @Override
    public Optional<User> findByUserName(String username) {
        return userRepository.findByUsername(username);
    }
}
