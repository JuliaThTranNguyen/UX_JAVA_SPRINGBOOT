package com.juliath.service.account.account;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AccountServiceLayer {

    private final  AccountRepository accountRepository;

    @Autowired
    public AccountServiceLayer(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> arraylist(){
        return accountRepository.findAll();
    }

    public Account getAccountsByID(Long accountID) {
        return accountRepository.findById(accountID).get();
    }

    public void addNewAccount(Account newaccount) {
        Optional<Account> findByEmail = accountRepository
                .findAccountByEmail(newaccount.getEmail());
        if(findByEmail.isPresent()){
            try {
                throw new IllegalAccessException("Email has been taken");
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        accountRepository.save(newaccount);
        System.out.println(newaccount);
    }

    public void deleteExistingAccount(Long accountID) {
        boolean exists = accountRepository.existsById(accountID);
        if (!exists) {
            try {
                throw new IllegalAccessException(
                        "Account with id: " + accountID + " does not exist"
                );
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        accountRepository.deleteById(accountID);
    }

    @Transactional
    public Account updateToAccount(Long accountID,Account newaccount ) {
        Account existingAccount = accountRepository.findById(accountID).orElse(null);
        if(existingAccount == null){
            try {
                throw new IllegalAccessException(
                        "Account with id: " +accountID +" can not be found"
                );
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        existingAccount.setName(newaccount.getName());
        existingAccount.setEmail(newaccount.getEmail());
        existingAccount.setDob(newaccount.getDob());

        return accountRepository.save(existingAccount);
    }



}
