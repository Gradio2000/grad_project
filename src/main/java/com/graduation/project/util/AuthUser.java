package com.graduation.project.util;

import com.graduation.project.model.User;
import org.springframework.lang.NonNull;


public class AuthUser extends org.springframework.security.core.userdetails.User {

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AuthUser(@NonNull User user) {
        super(user.getEmail(), user.getPassword(), user.getRoles());
        this.user = user;
    }

}