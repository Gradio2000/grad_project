package com.graduation.project.model.to;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserToWithoutPassword{
    @NotEmpty(message = "name mustn't be empty")
    private String name;

    @Email(message = "email isn't valid")
    @NotEmpty(message = "email mustn't be empty")
    @Size(max = 128, message = "size is too much")
    private String email;

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
}
