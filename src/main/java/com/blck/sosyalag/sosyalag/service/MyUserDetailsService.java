package com.blck.sosyalag.sosyalag.service;

import com.blck.sosyalag.sosyalag.model.SocialNetworkRuntimeException;
import com.blck.sosyalag.sosyalag.model.User;
import com.blck.sosyalag.sosyalag.model.UserPrincipal;
import com.blck.sosyalag.sosyalag.repo.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {


    private final UserRepo userRepository;

    public MyUserDetailsService(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new SocialNetworkRuntimeException(" user not found");
        }

        return new UserPrincipal(user);
    }
}
