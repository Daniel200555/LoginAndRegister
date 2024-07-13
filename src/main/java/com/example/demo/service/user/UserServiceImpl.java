package com.example.demo.service.user;

import com.example.demo.repository.UserRepository;
import com.example.demo.dto.RegisterDTO;
import com.example.demo.entity.User;
import com.example.demo.exception.CreateDirectoryException;
import com.example.demo.exception.UserFoundException;
import com.example.demo.service.connect.CreateDirectory;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired(required = true)
    private UserRepository userRepository;

    @Autowired(required = true)
    private PasswordEncoder passwordEncoder;

    @Autowired(required = true)
    private CreateDirectory createDirectory;


//    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, CreateDirectory createDirectory) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//        this.createDirectory = createDirectory;
//    }


    @Transactional
    public User createUser(RegisterDTO registerDTO) throws UserFoundException, MalformedURLException, CreateDirectoryException {
        User temp = userRepository.findUserByNickname(registerDTO.getNickname());
        if (temp != null)
            throw new UserFoundException("User With Nickname: " + registerDTO.getNickname()+ "EXIST!!!");
        User create = new User();
        create.setNickname(registerDTO.getNickname());
        create.setEmail(registerDTO.getEmail());
        create.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        userRepository.save(create);
        LOGGER.info("User with nickname: " + registerDTO.getNickname() + ", saved");

        return create;
    }

}
