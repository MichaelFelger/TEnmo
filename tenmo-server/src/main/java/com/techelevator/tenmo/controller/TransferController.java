package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.*;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.TransferDTO;
import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.security.jwt.TokenProvider;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.security.Principal;
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
    public BigDecimal getMyBalance(@PathVariable(name = "id") Long accountId) {
        return accountDao.getBalanceByAccountId(accountId);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/transfers")
    public void createTransfer(Principal principal, @RequestBody @Valid TransferDTO newTransfer) {
        int checkBalance = (newTransfer.getTransferAmount().compareTo(accountDao.getBalanceByUsername(principal.getName())));
        int checkNegative = newTransfer.getTransferAmount().compareTo(BigDecimal.ZERO);
        Transfer transfer = new Transfer((long) userDao.findIdByUsername(principal.getName()), newTransfer.getRecipientId(), newTransfer.getTransferAmount());
        if (checkBalance == -1 && checkNegative == 1) {
            transferDao.createTransfer(transfer);
            // update executeTransfer into here

        }
    }

    @RequestMapping(value = "/transfers/{username}", method = RequestMethod.GET)
    public List<Transfer> transferHistory(@PathVariable(name = "username") String username) {
        return transferDao.transferHistory(username);
    }

    
}
