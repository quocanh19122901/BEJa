package com.example.demo.response;

import com.example.demo.entity.AccountEntity;
import com.example.demo.entity.CourseEntity;
import com.example.demo.entity.ProfileEntity;
import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
public class AccountResponse {
    private Long id;
    private String username;
    private String email;
    private ProfileEntity profile;
    private List<CourseEntity> courses;

    public static AccountResponse toDomain(AccountEntity entity) {
        AccountResponse response = new AccountResponse();
        if (Objects.isNull(entity)) return response;
        response.setId(entity.getId());
        response.setUsername(entity.getUsername());
        response.setEmail(entity.getEmail());
        response.setProfile(entity.getProfile());
        response.setCourses(entity.getCourses());
        return response;
    }
    public static void main(String[] args) {
    }
}
