package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Component
public class JdbcTransferDao implements TransferDao {

    private JdbcTemplate jdbcTemplate;


    public JdbcTransferDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Transfer getTransfer(Long transferId) {
        String sql = "SELECT transfer_id, sender_account_id, recipient_account_id, status, amount FROM transfer WHERE transfer_id = ?;";
        SqlRowSet transferRowSet = jdbcTemplate.queryForRowSet(sql, transferId);
        if (transferRowSet.next()) {
            return mapRowToTransfer(transferRowSet);
        } else {
            return null;
        }
    }


    @Override
    public Transfer createTransfer(Transfer transfer) {
        String sql = "INSERT INTO transfer (sender_account_id, recipient_account_id, amount) VALUES (?, ?, ?) RETURNING transfer_id, date_and_time;";
        Long newTransferId;
        try {
            newTransferId = jdbcTemplate.queryForObject(sql, Long.class, transfer.getSenderId(), transfer.getRecipientId(), transfer.getTransferAmount());
        } catch (DataAccessException e) {
            return null;
            //Need to fix error handling, if no users, needs to return error message
        }
        return getTransfer(newTransferId);
    }


        @Override
        public List<Transfer> transferHistory (String username){
            List<Transfer> transferHistory = new ArrayList<>();
            String sql = "SELECT " +
                    " username, transfer_id, transfer.sender_account_id, transfer.recipient_account_id, amount, date_and_time, status" +
                    " FROM transfer" +
                    " JOIN account ON transfer.recipient_account_id = account.account_id" +
                    " OR transfer.sender_account_id = account.account_id" +
                    " JOIN tenmo_user ON account.user_id = tenmo_user.user_id" +
                    " WHERE username = ?";
            SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(sql, username); // transfer.getTransferId(), transfer.getSenderId(), transfer.getRecipientId(), transfer.getTransferAmount(), transfer.getTimestamp());
            while (sqlRowSet.next()) {
                Transfer transfer =  mapRowToTransfer(sqlRowSet);
                transferHistory.add(transfer);
            }
            return transferHistory;
            }


//        @Override
//        public Transfer recordTransfer (Transfer transfer){
//            return null;
//        }

        private Transfer mapRowToTransfer (SqlRowSet transferRowSet){
            Transfer transfer = new Transfer();
            transfer.setTransferId(transferRowSet.getLong("transfer_id"));
            transfer.setSenderId(transferRowSet.getLong("sender_account_id"));
            transfer.setRecipientId(transferRowSet.getLong("recipient_account_id"));
            transfer.setTimestamp(transferRowSet.getTimestamp("date_and_time"));
            transfer.setTransferStatus(transferRowSet.getString("status"));
            transfer.setTransferAmount(transferRowSet.getBigDecimal("amount"));

            return transfer;
        }
    }
