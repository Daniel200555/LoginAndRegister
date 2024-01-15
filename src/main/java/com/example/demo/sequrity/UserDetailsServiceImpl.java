package com.example.demo.sequrity;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userFind = userRepository.findUserByNickname(username);
        if (userFind == null)
            throw new UsernameNotFoundException("User with username: " + username + ", not FOUND!!!");

        return new org.springframework.security.core.userdetails.User(userFind.getNickname(), userFind.getPassword(), new ArrayList<>());
    }
}
