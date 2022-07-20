package com.luckgame.demo.service;

import com.luckgame.demo.user.AppUser;
import com.luckgame.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if(username == null){
            throw new UsernameNotFoundException("User not found");
        }

        userRepo.findByUsername(username);
        Optional<AppUser> userOptional = userRepo.findByUsername(username);

        userOptional.orElseThrow(() -> new UsernameNotFoundException("AppUser not found with username: " + username));

        return new MyUserDetails(userOptional.get());
    }
}

