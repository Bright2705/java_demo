package com.example.javaDemo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy =
        GenerationType.IDENTITY
    )
    private int id;

    private String product;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public Order() {

    }

    public Order(
            String product
    ) {

        this.product = product;

    }

    public int getId() {

        return id;

    }

    public String getProduct() {

        return product;

    }

    public void setProduct(
            String product
    ) {

        this.product = product;

    }

    public User getUser() {

        return user;

    }

    public void setUser(
            User user
    ) {

        this.user = user;

    }
}
