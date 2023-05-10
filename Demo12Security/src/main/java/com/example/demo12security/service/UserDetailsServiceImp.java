package com.example.demo12security.service;

import com.example.demo12security.config.UserDetailsImp;
import com.example.demo12security.entity.User;
import com.example.demo12security.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    UserRepo userRepo;
    @Autowired
    UserDetailsImp userDetailsImp;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepo.findUserByUserName(userName);
        if (user == null){
            throw new UsernameNotFoundException("user khong ton tai voi user name : "+userName);
        }
        return userDetailsImp.build(user);
    }
}
