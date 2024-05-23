package com.borisns.securitydemo.service;

import com.borisns.securitydemo.dto.response.UserDTO;
import com.borisns.securitydemo.model.User;

import java.util.List;

public interface UserService {

    User findById(Long id);
    UserDTO findByUsername(String username);
    List<UserDTO> findAll();

    User createUser(User user);

    User update(User user);

    void deleteById(Long id);

    void deleteAll();
}
