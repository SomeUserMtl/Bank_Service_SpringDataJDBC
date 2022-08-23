package com.example.sqch14ex1.repositories;

import com.example.sqch14ex1.models.Account;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.List;

public interface AccountRepository extends CrudRepository<Account, Long> {

    @Query("SELECT * FROM account")
    Iterable<Account> findAll();

    @Query("SELECT * FROM account WHERE name = :name")
    Iterable<Account> findAccountsByName(String name);

    @Query("SELECT * FROM account WHERE id = :id")
    List<Account> findAccountById(Long id);

    @Query("SELECT * FROM account WHERE id = :id AND name = :name")
    List<Account> findAccountByIdAndName(Long id, String name);

    @Modifying
    @Query("UPDATE account SET name = :name WHERE id = :id")
    void changeName(String name, Long id);

    @Modifying
    @Query("UPDATE account SET amount = :amount WHERE id = :id")
    void changeAmount(BigDecimal amount, Long id);
}
