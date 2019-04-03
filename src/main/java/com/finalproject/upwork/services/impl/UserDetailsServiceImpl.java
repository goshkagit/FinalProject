package com.finalproject.upwork.services.impl;

import com.finalproject.upwork.exception.NotFoundException;
import com.finalproject.upwork.models.UserLoginModel;
import com.finalproject.upwork.models.UserPrincipal;
import com.finalproject.upwork.repositories.UserLoginRepository;
import com.finalproject.upwork.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserLoginModel userLoginModel = userService.findByNickname(s);
        if (userLoginModel == null) {
            throw new NotFoundException("There is no user with username: " + s);
        }
        return new UserPrincipal(userLoginModel);
    }
}
