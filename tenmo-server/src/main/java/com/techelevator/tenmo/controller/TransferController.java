package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.*;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.security.jwt.TokenProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
public class TransferController {
    private TransferDao transferDao;
    private AccountDao accountDao;
    private UserDao userDao;

    public TransferController(TransferDao transferDao, AccountDao accountDao, UserDao userDao) {
        this.transferDao = transferDao;
        this.accountDao = accountDao;
        this.userDao = userDao;
    }


    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> listUsers() {
        return userDao.findAll();
    }


    @RequestMapping(value = "/users/{id}/balance", method = RequestMethod.GET)
    public BigDecimal getMyBalance(@PathVariable(name = "id") Long accountId){
        return accountDao.getBalanceByAccountId(accountId);
    }

    @RequestMapping(value = "/transfers", method = RequestMethod.POST)
    public Transfer createTransfer(@RequestBody Transfer transfer) {
        if (transfer != null) {
            transferDao.createTransfer(transfer);
            return transfer;
        }
        return null;
    }

    @RequestMapping(value = "/transfers/{username}", method = RequestMethod.GET)
    public List<Transfer> transferHistory(@PathVariable(name = "username") String username){
        return transferDao.transferHistory(username);
    }





}
