package com.borisns.userTest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.borisns.securitydemo.controller.UserController;
import com.borisns.securitydemo.dto.response.UserDTO;
import com.borisns.securitydemo.model.EventDetails;
import com.borisns.securitydemo.model.User;
import com.borisns.securitydemo.service.EventDetailsService;
import com.borisns.securitydemo.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @Mock
    private EventDetailsService eventDetailsService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testCreateUserWithEvent() throws Exception {
        User user = new User();
        EventDetails eventDetails = new EventDetails();
        user.setEventDetails(eventDetails);

        when(userService.createUser(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/user/api/create-user-with-event")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"eventDetails\": {}}"))
                .andExpect(status().isOk());

        verify(userService, times(1)).createUser(any(User.class));
    }

    @Test
    public void testGetAllUsers() throws Exception {
        List<UserDTO> userList = new ArrayList<>();
        userList.add(new UserDTO());

        when(userService.findAll()).thenReturn(userList);

        mockMvc.perform(get("/user/get-all-user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists());

        verify(userService, times(1)).findAll();
    }

    @Test
    public void testUpdateUser() throws Exception {
        User existingUser = new User();
        existingUser.setId(1L);
        User updatedUser = new User();
        updatedUser.setId(1L);
        updatedUser.setUsername("newUsername");

        when(userService.findById(1L)).thenReturn(existingUser);
        when(userService.update(any(User.class))).thenReturn(updatedUser);

        mockMvc.perform(put("/user/update-user/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"newUsername\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("newUsername"));

        verify(userService, times(1)).findById(1L);
        verify(userService, times(1)).update(any(User.class));
    }

    @Test
    public void testDeleteUserById() throws Exception {
        mockMvc.perform(delete("/user/deleteb-by-id/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("deleted user by Id Successfull"));

        verify(userService, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteAllUsers() throws Exception {
        mockMvc.perform(delete("/user/deleteall"))
                .andExpect(status().isOk())
                .andExpect(content().string("delete All User Data Successfull"));

        verify(userService, times(1)).deleteAll();
    }
}

