package com.ishan.reactivesystem.userservice.service;

import com.ishan.reactivesystem.userservice.dao.Transaction;
import com.ishan.reactivesystem.userservice.dao.TransactionRepository;
import com.ishan.reactivesystem.userservice.dao.TransactionStatus;
import com.ishan.reactivesystem.userservice.dao.UserRepository;
import com.ishan.reactivesystem.userservice.dto.TransactionDTO;
import com.ishan.reactivesystem.userservice.dto.TransactionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TransactionService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private TransactionRepository transactionRepository;

  public Mono<TransactionDTO> transact(TransactionRequest tr) {
    return userRepository.updateUserBalance(tr.getUserId(), tr.getAmount())
        .flatMap(updated -> {
          Transaction transaction = TransactionMapper.toTransaction(tr);
          if (updated) {
            transaction.setStatus(TransactionStatus.SUCCESS);
          } else {
            transaction.setStatus(TransactionStatus.FAILURE);
          }
          return transactionRepository.save(transaction);
        }).map(TransactionMapper::toTransactionDTO);
  }

  public Flux<TransactionDTO> getTransactionsByUser(String userId) {
    return transactionRepository.findAllByUserId(userId)
        .map(TransactionMapper::toTransactionDTO);
  }

}
