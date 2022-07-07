package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class JdbcAccountDao implements AccountDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public BigDecimal getBalanceByAccountId(Long accountId) {
        String sql = "SELECT balance FROM account WHERE account_id = ?;";
        BigDecimal balance = jdbcTemplate.queryForObject(sql, BigDecimal.class, accountId);
        return balance;
    }

    @Override
    public BigDecimal getBalanceByUsername(String username) {
        BigDecimal balance = null;
        String sql = "SELECT balance FROM account AS a JOIN tenmo_user AS u ON a.user_id = u.user_id WHERE username = ?;";
        balance = jdbcTemplate.queryForObject(sql, BigDecimal.class, username);

        return balance;
    }

    private Account mapRowToAccount(SqlRowSet rs){
        Account account = new Account();
        account.setId(rs.getLong("account_id"));
        account.setBalance(rs.getBigDecimal("balance"));
        account.setUserId(rs.getLong("user_id"));
        return account;
    }

}


