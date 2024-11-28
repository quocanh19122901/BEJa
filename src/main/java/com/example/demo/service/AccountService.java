package com.example.demo.service;

import com.example.demo.dto.loginDto;
import com.example.demo.dto.userDTO;
import com.example.demo.entity.AccountEntity;
import com.example.demo.entity.ProfileEntity;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.ProfileRespository;
import com.example.demo.request.SearchRequest;
import com.example.demo.response.AccountResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ProfileRespository profileRespository;

    public List<AccountResponse> getListAccount() {
        return accountRepository.findAll().stream()
                .map(AccountResponse::toDomain)
                .collect(Collectors.toList());
    }

    public AccountEntity findAccountById(Long id) {
        return accountRepository.findById(id).orElse(null);
    }

    public userDTO login(String username, String password) {
        AccountEntity account = accountRepository.findByUsername(username);
        if (account == null) {
            throw new IllegalArgumentException("Username doesn't exists");
        }
        if (!account.getPassword().equals(password)) {
            throw new IllegalArgumentException("Incorrect password");
        }
        return new userDTO(account.getId(), account.getUsername(), account.getEmail());
    }

    public void save(AccountEntity entity) {
        accountRepository.save(entity);
    }
    public List<AccountResponse> findUsername(String username) {
        return accountRepository.findByUsernameContains(username).stream()
                .map(AccountResponse::toDomain)
                .collect(Collectors.toList());
    }
    public List<AccountResponse> findUser(SearchRequest name) {
        List<AccountEntity> accountEntities = accountRepository.findUser(name.getEmail(), name.getUsername());
        return accountEntities.stream()
                .map(AccountResponse::toDomain).toList();

    }
    public loginDto createAccount(loginDto new_acc) {
        if (accountRepository.findByUsername(new_acc.getUsername()) != null) {
            throw new IllegalArgumentException("Username already exists");
        }
        if (accountRepository.findByEmail(new_acc.getEmail()) != null) {
            throw new IllegalArgumentException("Email already exists");
        }
        AccountEntity account = new AccountEntity();
        account.setEmail(new_acc.getEmail());
        account.setPassword(new_acc.getPassword());
        account.setUsername(new_acc.getUsername());
        account.setProfile(null);
        accountRepository.save(account);
        return new loginDto( account.getEmail(),account.getUsername(), account.getPassword());
    }

    public AccountResponse update(Long id) {
        ProfileEntity p = new ProfileEntity();
        p.setFirstName("2");
        p.setLastName("1");
        profileRespository.save(p);

        AccountEntity account = findAccountById(id);
        account.setProfile(p);
        accountRepository.save(account);

        return AccountResponse.toDomain(account);
    }
}


