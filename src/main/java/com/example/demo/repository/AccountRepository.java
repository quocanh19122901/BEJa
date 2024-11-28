package com.example.demo.repository;

import com.example.demo.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    AccountEntity findByUsername(String username);
    AccountEntity findByEmail(String email);
    List<AccountEntity> findByUsernameContains(String username);

//    @Query(value = "SELECT a FROM AccountEntity a WHERE a.email LIKE  %?1% AND a.username LIKE %?2%  ")
//    List<AccountEntity> findUser(String email, String username);

    @Query(value = "SELECT * FROM account WHERE email1 LIKE %:email% AND username LIKE %:username%  ", nativeQuery = true)
    List<AccountEntity> findUser(@Param("email") String email, @Param("username") String username);


}
