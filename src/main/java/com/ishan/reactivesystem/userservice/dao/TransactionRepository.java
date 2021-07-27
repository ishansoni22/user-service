package com.ishan.reactivesystem.userservice.dao;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface TransactionRepository extends ReactiveCrudRepository<Transaction, String> {

  Flux<Transaction> findAllByUserId(String userId);

}
