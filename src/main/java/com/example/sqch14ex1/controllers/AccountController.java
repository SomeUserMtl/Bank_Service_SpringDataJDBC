package com.example.sqch14ex1.controllers;

import com.example.sqch14ex1.models.Account;
import com.example.sqch14ex1.models.Transaction;
import com.example.sqch14ex1.services.TransferService;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {
    private final TransferService transferService;

    public AccountController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/transfer")
    public void transfer(@RequestBody Transaction transaction) {
        transferService.transferMoney(
                transaction.getSender(),
                transaction.getReceiver(),
                transaction.getAmount());
    }

    @GetMapping("/account")
    public Iterable<Account> accountByIdOrName(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long id) {

        if(name == null && id == null) {
            return transferService.getAllAccounts();
        }
        else if(name == null || id == null){
            if (name == null)
                return transferService.findAccountsByID(id);
            return transferService.findAccountsByName(name);
        }

        return transferService.findAccountByIdAndName(id, name);
    }
}
