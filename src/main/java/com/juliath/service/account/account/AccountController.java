package com.juliath.service.account.account;

import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping
    public void registerNewAccount(@RequestBody Account newaccount){
        getAccountServiceLayer.addNewAccount(newaccount);
    }

    @DeleteMapping(path="{accountID}")
    public void deleteAccount(@PathVariable("accountID") Long accountID){
        getAccountServiceLayer.deleteExistingAccount(accountID);
    }

    @PutMapping(path = "{accountID}")
    public void updateToAccount(
            @PathVariable("accountID") Long accountID,
            @RequestParam(required = false)String name,
            @RequestParam(required = false)String email
    ) {
        getAccountServiceLayer.updateToAccount(accountID, name, email);
    }
}
