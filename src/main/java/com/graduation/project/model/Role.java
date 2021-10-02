package com.graduation.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public enum Role {

    ADMIN_Role,
    USER_Role;

    @Id
    @Column(name = "role_id")
    private int id;

    @Column
    private int user_id;
}
