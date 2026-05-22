package com.example.javaDemo.controller_api;

import com.example.javaDemo.Model.Order;
import com.example.javaDemo.Model.User;
import com.example.javaDemo.dto.CreateUserRequest;
import com.example.javaDemo.dto.UserResponse;
import com.example.javaDemo.service.UserSevice;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    private UserSevice userSevice;

    public UserController(UserSevice sevice){
        userSevice = sevice;
    }

    @GetMapping("/usersList")
    public List<User> getUsers() {
        return userSevice.getAll();
    }
    @GetMapping("/users/{id}")
    public Object getUserById(@PathVariable Long id) {
        return userSevice.getUserById(id);
    }

    @GetMapping("/users/{name}")
    public List<User> getUserByName(@PathVariable String name) {
        return userSevice.getUserByName(name);
    }

    @GetMapping("/users/search")
    public List<User> searchUserByName(@RequestParam String name) {
        return userSevice.searchUserByName(name);
    }

    @GetMapping("/users/get10Users")
    public Page<User> getUsersPage(@RequestParam int page, @RequestParam int size){
        return userSevice.getUsers(0,10);
    }

    @PostMapping("/addUser")
    public User addUser(@RequestBody User user){
        return userSevice.addUser(user);
    }

    @PostMapping("/addUserAndOrder")
    public void addUserAndOrder(@RequestBody Map<String,Object> body){
        userSevice.addUserAndOrder(body);
    }

    @GetMapping("getOrderByUserId/{id}")
    public List<Order> getOrderByUserId(@PathVariable Long id){
        return userSevice.getOrderByUserId(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id){
        User user = (User) userSevice.getUserById(id);
        userSevice.delete(user);
    }

    @PostMapping("/createUser")
    public UserResponse create(
            @Valid
            @RequestBody
            CreateUserRequest request

    ) {
        return userSevice.create(request);
    }
}
