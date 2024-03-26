package com.assignment.user.controller;

import com.assignment.user.entity.UserDetails;
import com.assignment.user.service.UserService;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@RequestMapping("/api/v1/user")
@RestController
@Configuration
public class UserController {
    private final UserService userService;
    private final MeterRegistry meterRegistry;
    private final Counter getAllUsersRequestCounter;
    private final Counter getUserByIdRequestCounter;
    private final Counter addUserRequestCounter;
    private final Counter updateUserRequestCounter;

    private final Counter errorCounterForGetAllUsers;
    private final Counter errorCounterForGetUserById;
    private final Counter errorCounterForAddUserDetails;
    private final Counter errorCounterForUpdateUserDetails;

    @Autowired
    public UserController(UserService userService, MeterRegistry meterRegistry) {
        this.userService = userService;
        this.meterRegistry = meterRegistry;

        // Initialize counters
        getAllUsersRequestCounter = meterRegistry.counter("api.v1.user.allusers.requests");
        getUserByIdRequestCounter = meterRegistry.counter("api.v1.user.user.requests");
        addUserRequestCounter = meterRegistry.counter("api.v1.user.adduser.requests");
        updateUserRequestCounter =meterRegistry.counter("api.v1.user.updateuser.requests");

        errorCounterForGetAllUsers = meterRegistry.counter("api.v1.user.getAllErrors");
        errorCounterForGetUserById=meterRegistry.counter("api.v1.user.getUserByIdErrors");
        errorCounterForAddUserDetails=meterRegistry.counter("api.v1.user.addUserErrors");
        errorCounterForUpdateUserDetails=meterRegistry.counter("api.v1.user.updateUserErrors");
    }

    // Get all user details
    @GetMapping("/allusers")
    @Timed(value="getAllUsers.time", description = "time taken to retrieve all user details")
    public ResponseEntity<List<UserDetails>> getAllUserDetails() {
        try {
            List<UserDetails> userDetailsList = userService.getAllUsers();
            getAllUsersRequestCounter.increment();
            return ResponseEntity.ok(userDetailsList);
        } catch (Exception e) {
            errorCounterForGetAllUsers.increment();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Get user details by ID
    @GetMapping("/user/{id}")
    @Timed(value="getUserById.time", description = "time taken to retrieve user details by id")
    public ResponseEntity<UserDetails> getUserDetailsById(@PathVariable("id") long id) {
        try {
            Optional<UserDetails> userDetailsOptional = userService.getUserById(id);
            getUserByIdRequestCounter.increment();
            return userDetailsOptional.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            errorCounterForGetUserById.increment();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Add new user details
    @PostMapping("/adduser")
    @Timed(value="addUserDetails.time", description = "time taken to add user details ")
    public ResponseEntity<UserDetails> addDetails(@RequestBody UserDetails userDetails) {
        try {
            UserDetails savedUserDetails = userService.addUserDetails(userDetails);
            addUserRequestCounter.increment();
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUserDetails);
        } catch (Exception e) {
            errorCounterForAddUserDetails.increment();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

//     Update user details
    @PutMapping("/updateuser/{email}")
    @Timed(value="updateUserDetails.time", description = "time taken to update user details ")
    public ResponseEntity<UserDetails> updateUserDetails(@PathVariable("email") String email, @RequestBody UserDetails userDetails) {
        try {
            Optional<UserDetails> userDetailsOptional = userService.findUserDetailsByEmail(email);
            if (userDetailsOptional.isPresent()) {
                Optional<UserDetails> updatedUserDetails = userService.updateUserByEmail(email,userDetails);
                updateUserRequestCounter.increment();
                return ResponseEntity.ok(updatedUserDetails.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            errorCounterForUpdateUserDetails.increment();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
