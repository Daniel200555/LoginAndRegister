package com.example.demo.controller;

import com.example.demo.dto.LoginDTO;
import com.example.demo.dto.RegisterDTO;
import com.example.demo.entity.User;
import com.example.demo.exception.CreateDirectoryException;
import com.example.demo.exception.UserFoundException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.connect.CreateDirectory;
import com.example.demo.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class RegisterLoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CreateDirectory createDirectory;

//    public RegisterController(UserServiceImpl userService) {
//        this.userService = userService;
//    }

    @PostMapping("/login/")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getNickname(), loginDTO.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
//        return new ResponseEntity<>("User Login Successfully!!!!!!!!", HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/register/")
    public ResponseEntity register(@RequestBody RegisterDTO registerDTO) throws MalformedURLException, UserFoundException, CreateDirectoryException {
        userService.createUser(registerDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/dir")
    public Map<String, Boolean> dir(@RequestParam String path) throws MalformedURLException, CreateDirectoryException {
        Map<String, Boolean> create = new HashMap<>();
        createDirectory.create(path);
        create.put("create directory", true);
        return create;
    }


    @GetMapping("/find")
    public User find(@RequestParam String nick) {
        return userRepository.findUserByNickname(nick);
    }

}
