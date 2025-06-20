package com.app.FundTheStory.Entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String role;
    private String password;


    // No args Constructor for JPA
    public User(){}

    public User(String name, String role, String password){
        this.name = name;
        this.role = role;
        this.password = password;
    }


    public String getName() {
        return name;
    }
    public Long getId(){
        return id;
    }
    public String getPassword() {
        return password;
    }

    public String getRole(){
        return role;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPassword(String password){
        this.password = password;
    }
    public void setId(Long id){
        this.id = id;
    }
    public void setRole(String role){
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
