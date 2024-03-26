package com.assignment.user.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.assignment.user.entity.UserDetails;
import com.assignment.user.repository.UserRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class UserServiceTests {

    @MockBean
    UserRepository userRepository;
    @Autowired
    UserService userService;

    UserDetails userDetails;

    List<UserDetails> userList=null;
    @BeforeEach
    public void setUp() {
        userDetails=new UserDetails(1,"john_doe","John Doe","john.doe@example.com","30","1234567890","Hyderabad");
    }

    @Test
    public void testGetAllUsers() {
        // Mock data
        userList = new ArrayList<>();
        userList.add(userDetails);
        when(userRepository.findAll()).thenReturn(userList);

        // Test
        List<UserDetails> result = userService.getAllUsers();
        assertEquals(1, result.size());
    }

    @Test
    public void testGetUserById() {
        // Mock data
        when(userRepository.findById(1L)).thenReturn(Optional.of(userDetails));
        // Test
        Optional<UserDetails> result = userService.getUserById(1L);
        assertTrue(result.isPresent());
        assertEquals("john_doe", result.get().getUsername());
    }

    @Test
    public void testAddUserDetails() {
        // Mock data

        when(userRepository.save(userDetails)).thenReturn(userDetails);

        // Test
        UserDetails result = userService.addUserDetails(userDetails);
        assertEquals(userDetails, result);
    }

    @Test
    public void testUpdateUserByEmail() {
        // Mock data

        when(userRepository.findByEmail("john@example.com")).thenReturn(userDetails);
        when(userRepository.save(userDetails)).thenReturn(userDetails);

        // Test
        Optional<UserDetails> result = userService.updateUserByEmail("john@example.com", userDetails);
        System.out.println(result);
        assertTrue(result.isPresent());
        assertEquals("John Doe", result.get().getFullName());
    }

    @Test
    public void testFindUserDetailsByEmail() {
        // Mock data

        when(userRepository.findByEmail("john@example.com")).thenReturn(userDetails);

        // Test
        Optional<UserDetails> result = userService.findUserDetailsByEmail("john@example.com");
        assertTrue(result.isPresent());
        assertEquals("John Doe", result.get().getFullName());
    }

}
