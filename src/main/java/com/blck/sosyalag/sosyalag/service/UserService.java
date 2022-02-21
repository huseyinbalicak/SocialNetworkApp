package com.blck.sosyalag.sosyalag.service;

import com.blck.sosyalag.sosyalag.model.SocialNetworkRuntimeException;
import com.blck.sosyalag.sosyalag.model.User;
import com.blck.sosyalag.sosyalag.repo.UserRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final BCryptPasswordEncoder encoder;
    private final UserRepo userRepository;

    public UserService(BCryptPasswordEncoder encoder, UserRepo userRepository) {
        this.encoder = encoder;
        this.userRepository = userRepository;
    }

    public void save(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public User getUser(Long id) {
        Optional<User> optional = userRepository.findById(id);
        User user;
        if (optional.isPresent()) {
            user = optional.get();
        } else {
            throw new SocialNetworkRuntimeException(" user not found for id :: " + id);
        }
        return user;
    }

    public List<User> listAll() {
        return userRepository.findAll();
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }


    public User fetchUserByEmail(String email)
    {
        return userRepository.getUserByEmail(email);
    }

    public List<User> finByUserName(String fullName) {
        return userRepository.findByFullName(fullName);
    }




}
