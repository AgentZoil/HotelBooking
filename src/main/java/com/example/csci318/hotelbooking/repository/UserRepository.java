package com.example.csci318.hotelbooking.repository;

import com.example.csci318.hotelbooking.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
}
