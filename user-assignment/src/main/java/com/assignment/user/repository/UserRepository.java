package com.assignment.user.repository;

import com.assignment.user.entity.UserDetails;
import com.assignment.user.service.UserService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserDetails,Long> {

    UserDetails findByEmail(String email);
}
