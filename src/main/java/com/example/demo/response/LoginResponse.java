package com.example.demo.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse<T> {
    private T dataUser;
    private String message;

    public LoginResponse(String message) {
        this.message = message;
    }
}
