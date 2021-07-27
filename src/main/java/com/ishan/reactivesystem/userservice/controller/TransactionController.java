package com.ishan.reactivesystem.userservice.controller;

import com.ishan.reactivesystem.userservice.dto.TransactionDTO;
import com.ishan.reactivesystem.userservice.dto.TransactionRequest;
import com.ishan.reactivesystem.userservice.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/transactions")
public class TransactionController {

  @Autowired
  private TransactionService transactionService;

  @PostMapping
  public Mono<TransactionDTO> transact(@RequestBody Mono<TransactionRequest> tr) {
    return tr.flatMap(transactionService::transact);
  }

  @GetMapping("/users/{userId}")
  public Flux<TransactionDTO> getTransactionsByUser(@RequestParam("id") String userId) {
    return transactionService.getTransactionsByUser(userId);
  }

}
