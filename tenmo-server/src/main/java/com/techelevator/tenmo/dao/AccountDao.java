package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

import java.math.BigDecimal;

public interface AccountDao {

BigDecimal getBalanceByAccountId(Long accountId);


}
