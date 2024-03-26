package com.assignment.user.service;

import com.assignment.user.entity.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    List<UserDetails> getAllUsers();
    Optional<UserDetails> getUserById(long id) ;
    UserDetails addUserDetails(UserDetails userDetails);
    Optional<UserDetails> updateUserByEmail(String email,UserDetails userDetails);

    Optional<UserDetails> findUserDetailsByEmail(String email);
}
