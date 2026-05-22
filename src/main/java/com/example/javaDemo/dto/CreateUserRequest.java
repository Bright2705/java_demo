package com.example.javaDemo.dto;

import jakarta.validation.constraints.*;

public class CreateUserRequest {

    @NotBlank
    private String name;

    @Min(18)
    private int age;

    public void CreateUserRequest(){

    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public int getAge(){
        return age;
    }
    public void setAge(int age){
        this.age = age;
    }
}
