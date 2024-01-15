package com.example.demo.service.user;

import com.example.demo.entity.User;
import com.example.demo.dto.RegisterDTO;
import com.example.demo.exception.CreateDirectoryException;
import com.example.demo.exception.UserFoundException;

import java.net.MalformedURLException;

public interface UserService {

    User createUser(RegisterDTO registerDTO) throws UserFoundException, MalformedURLException, CreateDirectoryException;

}
