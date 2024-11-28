package com.example.demo.entity;

import lombok.Data;
import org.hibernate.annotations.Columns;

import java.sql.Date;

@Data
public class UserEntity {
    private int id;
    private String username;
    private String email;
    private String password;
    private String first_name;
    private String last_name;
    private Date create_at;
    private Date update_at;
    private boolean isActive;
}
