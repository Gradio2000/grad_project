package com.graduation.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_role")
public class UserRole {

    @Id
    @Column(name = "user_role_id")
    private int id;

    @Column
    private int user_id;

    @Column
    private int role_id;

    public UserRole(int id, int user_id, int role_id) {
        this.id = id;
        this.user_id = user_id;
        this.role_id = role_id;
    }

    public UserRole() {

    }
}
