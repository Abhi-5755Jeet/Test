package com.borisns.securitydemo.service.impl;

import com.borisns.securitydemo.dto.response.UserDTO;
import com.borisns.securitydemo.exception.exceptions.ApiRequestException;
import com.borisns.securitydemo.model.EventDetails;
import com.borisns.securitydemo.model.User;
import com.borisns.securitydemo.repository.UserRepository;
import com.borisns.securitydemo.service.EventDetailsService;
import com.borisns.securitydemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventDetailsService eventDetailsService;

    @Override
    public User findById(Long id) throws ApiRequestException {
        try {
            User user = userRepository.findById(id).get();
           return user;
        } catch (NoSuchElementException e) {
            throw new ApiRequestException("User with id '" + id + "' doesn't exist.");
        }
    }

    @Override
    public UserDTO findByUsername(String username) throws ApiRequestException {
        try {
            User user = userRepository.findByUsername(username);
            return new UserDTO(user);
        } catch (UsernameNotFoundException e) {
            throw new ApiRequestException("User with username '" + username + "' doesn't exist.");
        }
    }

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream()
                .map(user -> new UserDTO(user)).collect(Collectors.toList());
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }


//    @Override
//    public User createUser(User user, EventDetails eventDetails) {
//        user.setEventDetails(eventDetails);
//
//       EventDetails eventDetails1= eventDetailsService.save(user.getEventDetails());
//       user.setEventDetails(eventDetails1);
//        return userRepository.save(user);
//    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }
}
