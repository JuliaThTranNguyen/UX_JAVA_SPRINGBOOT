package com.juliath.service.account.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/account")
public class AccountController {

    private final AccountServiceLayer getAccountServiceLayer;

    @Autowired
    public AccountController(AccountServiceLayer getAccountServiceLayer) {
        this.getAccountServiceLayer = getAccountServiceLayer;
    }

    @GetMapping
    public List<Account> arraylist(){
        return getAccountServiceLayer.arraylist();
    }

    @GetMapping("/user/{accountID}")
    public Account getAccountsByID(@PathVariable("accountID") Long accountID){
        return getAccountServiceLayer.getAccountsByID(accountID);
    }

    @PostMapping
    public void registerNewAccount(@RequestBody Account newaccount){
        getAccountServiceLayer.addNewAccount(newaccount);
    }

    @DeleteMapping(path="/user/{accountID}")
    public void deleteAccount(@PathVariable("accountID") Long accountID){
        getAccountServiceLayer.deleteExistingAccount(accountID);
    }

    @PutMapping(path = "/user/{accountID}")
    public ResponseEntity<Account> updateToAccount(
            @PathVariable("accountID") Long accountID,
            @RequestBody Account newaccount
    ) {
        Account updateAccountByID = getAccountServiceLayer.updateToAccount(accountID, newaccount);
        return new ResponseEntity<>(updateAccountByID, HttpStatus.OK);
    }

}
