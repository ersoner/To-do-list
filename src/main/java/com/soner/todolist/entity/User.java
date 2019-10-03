package com.soner.todolist.entity;

import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name= "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min=2, max=255, message = "Full name is required")
    private String full_name;

    @NotNull
    //@UniqueElements
    @Size(min=2, max=255, message = "Email is required")
    private String email;

    @NotNull
    @Size(min=2, max=255, message = "Password is required")
    private String password;

    private String created_at;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String name) {
        this.full_name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String name) {
        this.email = email;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public User(){}

    public User(String full_name, String password, String email)
    {
        this.full_name = full_name;
        this.password = password;
        this.email = email;
    }
}
