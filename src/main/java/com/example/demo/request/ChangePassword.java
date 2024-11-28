package com.example.demo.request;

import lombok.Data;

@Data
public class ChangePassword {
    private String username;
    private String currentPassword;
    private String newPassword;
}
