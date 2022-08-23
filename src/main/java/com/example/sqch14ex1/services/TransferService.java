package com.example.sqch14ex1.services;

import com.example.sqch14ex1.models.Account;
import com.example.sqch14ex1.models.Transaction;
import com.example.sqch14ex1.repositories.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransferService {

    private final AccountRepository accountRepository;

    public TransferService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public void transferMoney(Long senderId, Long receiverId, BigDecimal amount){
       Account senderAcct = accountRepository.findAccountById(senderId).get(0);
       Account receiverAcct = accountRepository.findAccountById(receiverId).get(0);

       BigDecimal newSenderAmt = senderAcct.getAmount().subtract(amount);
       BigDecimal newReceiverAmt = receiverAcct.getAmount().add(amount);

       accountRepository.changeAmount(newSenderAmt, senderId);
       accountRepository.changeAmount(newReceiverAmt, receiverId);
    }

    public Iterable<Account> getAllAccounts(){
        return accountRepository.findAll();
    }

    public Iterable<Account> findAccountsByName(String name) {
        return accountRepository.findAccountsByName(name);
    }

    public List<Account> findAccountsByID(Long id) {
        return accountRepository.findAccountById(id);
    }

    public List<Account> findAccountByIdAndName(Long id, String name){
        return accountRepository.findAccountByIdAndName(id,name);
    }

}
