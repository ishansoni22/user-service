package com.ishan.reactivesystem.userservice.service;

import com.ishan.reactivesystem.userservice.dao.Transaction;
import com.ishan.reactivesystem.userservice.dto.TransactionDTO;
import com.ishan.reactivesystem.userservice.dto.TransactionRequest;
import org.springframework.beans.BeanUtils;

public final class TransactionMapper {

  private TransactionMapper() {

  }

  public static Transaction toTransaction(TransactionRequest transactionRequest) {
    Transaction transaction = new Transaction();
    BeanUtils.copyProperties(transactionRequest, transaction);
    return transaction;
  }

  public static TransactionDTO toTransactionDTO(Transaction transaction) {
    TransactionDTO transactionDTO = new TransactionDTO();
    BeanUtils.copyProperties(transaction, transactionDTO);
    return transactionDTO;
  }

}
