package com.example.demo.controller;

import com.example.demo.dto.loginDto;
import com.example.demo.dto.userDTO;
import com.example.demo.request.ChangePassword;
import com.example.demo.request.SearchRequest;
import com.example.demo.response.AccountResponse;
import com.example.demo.response.LoginResponse;
import com.example.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/list_account")
    public List<AccountResponse> getListAccount() {
        return accountService.getListAccount();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> getAccountById(@PathVariable Long id) {
        AccountResponse account = AccountResponse.toDomain(accountService.findAccountById(id));
        return ResponseEntity.ok(account);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerAccount(@RequestBody loginDto account) {
        try {
            loginDto newAccount = accountService.createAccount(account);
            return ResponseEntity.ok(newAccount);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse<userDTO>> login(@RequestBody loginDto loginReq) {
        try {
            userDTO account = accountService.login(loginReq.getUsername(), loginReq.getPassword());
            return ResponseEntity.ok(new LoginResponse<>(account, "Login successfully"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new LoginResponse<>(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new LoginResponse<>(e.getMessage()));
        }
    }

    @GetMapping("/find/{username}")
    public List<AccountResponse> findUsername(@PathVariable String username) {
        return accountService.findUsername(username);
    }

    @GetMapping("/find")
    public List<AccountResponse> findUser(@RequestBody SearchRequest name) {
        return accountService.findUser(name);
    }

    @PostMapping("/update/{id}")
    public AccountResponse updateAccountById(@PathVariable Long id) {
        return accountService.update(id);
    }
}
