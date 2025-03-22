package com.example.java_server.controller;
import com.example.java_server.services.UserServices;
import org.springframework.web.bind.annotation.*;
import com.example.java_server.models.User;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/user")
public class UserController {

    private final UserServices userServices;

    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping("/get-all")
    public List<User> GetAllUser(){
        return userServices.getAllUsers();
    }

    @PostMapping("/create")
    public User createUser(@RequestBody User user){
        return userServices.createUser(user);
    }

    @GetMapping("/get/{id}")
    public User getUser(@PathVariable String id){
        return userServices.getUserById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable String id){
        return userServices.deleteUserById(id);
    }

    @PutMapping("/update/{id}")
    public Optional<User> updateUser(@RequestBody User user, @PathVariable String id){
        return userServices.updateUser(user, id);
    }

}
