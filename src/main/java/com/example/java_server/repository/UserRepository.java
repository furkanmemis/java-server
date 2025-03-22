package com.example.java_server.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.java_server.models.User;

public interface UserRepository extends MongoRepository<User, String> {
}
