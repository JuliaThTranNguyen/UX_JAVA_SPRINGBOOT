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
    public void updateToAccount(Long accountID, String name, String email) {
        Account updateAccountTo = accountRepository.findById(accountID)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Student with id: "+ accountID + " does not exist"
                ));
        if(name != null &&
            name.length() > 0 &&
            !Objects.equals(updateAccountTo.getName(), name)
        ){
            updateAccountTo.setName(name);
        }

        if(email != null &&
                email.length() > 0 &&
                !Objects.equals(updateAccountTo.getEmail(), email)
        ){
            Optional<Account> AccountOptional = accountRepository
                    .findAccountByEmail(email);
            if(AccountOptional.isPresent()) {
                try {
                    throw new IllegalAccessException("Email has been taken");
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            updateAccountTo.setEmail(email);
        }
    }
}
