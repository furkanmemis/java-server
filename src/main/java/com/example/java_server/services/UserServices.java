package com.example.java_server.services;

import com.example.java_server.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import com.example.java_server.models.User;
import org.springframework.stereotype.Service;
import com.example.java_server.services.HashServices;

@Service
public class UserServices {

    private final UserRepository userRepository;
    private final HashServices hashServices;
    public UserServices(UserRepository userRepository, HashServices hashServices1) {
        this.userRepository = userRepository;
        this.hashServices = hashServices1;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        user.setPassword(hashServices.Hash(user.getPassword()));
        return userRepository.save(user);
    }

    public User getUserById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    public String deleteUserById(String id) {
        User user = getUserById(id);
        if (user != null) {
            userRepository.delete(user);
            return "User delete success";
        }
        return "User not found with id: " + id;
    }

    public Optional<User> updateUser(User user, String id) {
        User currentUser = getUserById(id);

        if(currentUser != null) {

            if(user.getName() != null){
                currentUser.setName(user.getName());
            }
            if(user.getEmail() != null){
                currentUser.setEmail(user.getEmail());
            }
            if(user.getPassword() != null){
                currentUser.setPassword(hashServices.Hash(user.getPassword()));
            }
            if(user.getSurname() != null){
                currentUser.setSurname(user.getSurname());
            }

            userRepository.save(currentUser);

            return Optional.of(currentUser);
        }

        return null;
    }
}
