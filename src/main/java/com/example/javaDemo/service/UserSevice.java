package com.example.javaDemo.service;

import com.example.javaDemo.Model.Order;
import com.example.javaDemo.Model.User;
import com.example.javaDemo.dto.RegisterRequest;
import com.example.javaDemo.exception.UserNotFoundException;
import com.example.javaDemo.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.javaDemo.dto.UserResponse;
import com.example.javaDemo.dto.CreateUserRequest;

import java.util.List;
import java.util.Map;

@Service
public class UserSevice {

    private final UserRepository repo;

    private EmailSevice emailSevice;
    private AuthService authService;

    public UserSevice(UserRepository repository) {
        this.repo = repository;
    }

    public List<User> getAll() {
        return repo.findAll();
    }

    public Object getUserById(Long id){
        return repo.findById(id);
    }

    public List<User> getUserByName(String name){
        return repo.findByName(name);
    }
    public List<User> searchUserByName(String name){
        return repo.findByNameContaining(name);
    }

    public Page<User> getUsers(
            int page,
            int size
    ) {

        Pageable pageable =
                PageRequest.of(page, size, Sort.by("name"));

        return repo.findAll(
                pageable
        );

    }

    public void delete(User user){
        repo.delete(user);
    }

    public User addUser(User user) {

        if(user.getName().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"name shouldn't be empty");
        }
        return repo.save(user);
    }
    public void addUserAndOrder(Map<String,Object> body) {

        if(body.get("name").toString().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"name shouldn't be empty");
        }
        String nameUser = body.get("name").toString();
        int ageUser = (int) body.get("age");
        List<String> productsList = (List<String>) body.get("products");
        User user = new User(nameUser, ageUser);
        for(String product : productsList){
            Order order = new Order(product);
            order.setUser(user);
            user.getOrders().add(order);
        }
        repo.save(user);
    }

    public List<Order> getOrderByUserId(Long id){
        return repo.findById(id).get().getOrders();
    }

    public UserResponse create(
            CreateUserRequest request
    ) {
        User user = new User();
        user.setName( request.getName()
        );
        user.setAge( request.getAge()
        );
        User saved = repo.save(user);
        return new UserResponse(
                saved.getId(),
                saved.getName(),
                saved.getAge()
        );
    }
    public User getUser(Long id) {
        return repo.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User not found"
                        )
                );
    }

    public void registerAccount(RegisterRequest request){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        User user = new User();

        user.setUsername(request.getUsername());

        user.setPassword(encoder.encode( request.getPassword() ));

        user.setRole("ROLE_USER");

        repo.save(user);
    }
}
