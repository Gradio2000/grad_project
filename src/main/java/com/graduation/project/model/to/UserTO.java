package com.graduation.project.model.to;

import com.graduation.project.model.Role;
import com.graduation.project.model.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.EnumSet;

public class UserTO {

    @NotEmpty(message = "name mustn't be empty")
    private String name;

    @Email(message = "email isn't valid")
    @NotEmpty(message = "email mustn't be empty")
    @Size(max = 128, message = "size is too much")
    private String email;


    @Size(max = 256, message = "size is too much")
    @NotEmpty(message = "password mustn't be empty")
    private String password;


    public UserTO(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getUser(UserTO userTO){
        return new User(name, email.toLowerCase(), password, EnumSet.of(Role.USER));
    }


}