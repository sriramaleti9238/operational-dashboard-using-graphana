package com.assignment.user.service;

import com.assignment.user.entity.UserDetails;
import com.assignment.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<UserDetails> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserDetails> getUserById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public UserDetails addUserDetails(UserDetails userDetails) {
        return userRepository.save(userDetails);
    }

    @Override
    public Optional<UserDetails> updateUserByEmail(String email,UserDetails userDetails) {
        Optional<UserDetails> optionalUser = Optional.ofNullable(userRepository.findByEmail(email));

        if (optionalUser.isPresent()) {
            UserDetails existingUser = optionalUser.get();

            // Update user details
            existingUser.setUsername(userDetails.getUsername());
            existingUser.setFullName(userDetails.getFullName());
            existingUser.setAge(userDetails.getAge());
            existingUser.setMblNumber(userDetails.getMblNumber());
            existingUser.setAddress(userDetails.getAddress());

            // Save the updated user details
            UserDetails updatedUser = userRepository.save(existingUser);
            return Optional.of(updatedUser);
        } else {
            // User with the specified email does not exist
            return Optional.empty();
        }
    }

    @Override
    public Optional<UserDetails> findUserDetailsByEmail(String email) {
        return Optional.of(userRepository.findByEmail(email));
    }
}
