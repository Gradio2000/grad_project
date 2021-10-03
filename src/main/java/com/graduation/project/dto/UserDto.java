package com.graduation.project.dto;

import com.graduation.project.model.Role;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class UserDto {
    private String name;
    private List<Role> roleList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
}
