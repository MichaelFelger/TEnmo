package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;

import java.util.List;

public interface TransferDao {

    Transfer getTransfer(Long transferId);

    Transfer createTransfer(Transfer transfer);

    List<Transfer>transferHistory(String username);

//    Transfer recordTransfer(Transfer transfer);






}
