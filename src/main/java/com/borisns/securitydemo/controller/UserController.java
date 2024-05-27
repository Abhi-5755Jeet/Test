package com.borisns.securitydemo.controller;


import com.borisns.securitydemo.dto.response.UserDTO;
import com.borisns.securitydemo.model.EventDetails;
import com.borisns.securitydemo.model.User;
import com.borisns.securitydemo.service.EventDetailsService;
import com.borisns.securitydemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private EventDetailsService eventDetailsService;

    @PostMapping("/api/create-user-with-event")
    public void createUserWithEvent(@RequestBody User user) {
        EventDetails eventDetails= user.getEventDetails();
        user.setEventDetails(eventDetails);
        User createdUser = userService.createUser(user);
    }


    @GetMapping("/get-all-user")
    public ResponseEntity<List<UserDTO>> getAll(){
        return ResponseEntity.ok(userService.findAll());
    }

    @PutMapping("/update-user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {

       User user1 = userService.findById(id);

        user1.setUsername(user.getUsername());
        user1.setFirstName(user.getFirstName());
        user1.setLastName(user.getLastName());
        user1.setEmail(user.getEmail());
        user1.setEnabled(user.isEnabled());

        User updatedUser = userService.update(user1);

        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/deleteb-by-id/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        userService.deleteById(id);
        return ResponseEntity.ok("deleted user by Id Successfull");

    }

    @DeleteMapping("/deleteall")
    public ResponseEntity<String> deleteAllUser(){

        userService.deleteAll();
        return ResponseEntity.ok("delete All User Data Successfull");
    }

}
