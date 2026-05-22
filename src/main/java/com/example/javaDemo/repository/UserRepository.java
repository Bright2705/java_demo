package com.example.javaDemo.repository;
import com.example.javaDemo.Model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    List<User> findByName(
            String name
    );

    List<User> findByNameContaining(
            String keyword
    );

    Page<User> findAll(
            Pageable pageable
    );

    @Query(
            "SELECT u FROM User u " +
                    "WHERE u.age > :age"
    )
    List<User> findAdults(
            @Param("age") int age
    );

    Optional<User> findByUsername(
            String username
    );
}
